package com.moseeker.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import com.moseeker.exception.BaseException;

/**
 * @category Excel工具类
 */
public final class ExcelUtil {

	public static final String excel2003 = "xls";
	public static final String excel2007 = "xlsx";

	public static final void writeExcel(String excelType, List<Map<Integer, String>> data, OutputStream os) throws Exception {
		if (!excel2003.equalsIgnoreCase(excelType) && !excel2007.equalsIgnoreCase(excelType)) {
			throw new BaseException(C.EMPTY, "格式不正确");
		}
		Workbook book = null;
		if (excel2003.equalsIgnoreCase(excelType)) {
			book = new HSSFWorkbook();
		} else if (excel2007.equalsIgnoreCase(excelType)) {
			if (CollectionUtils.isNotEmpty(data) && data.size() > 50000) {
				book = new SXSSFWorkbook();
			} else {
				book = new XSSFWorkbook();
			}
		}
		try {
			Sheet sheet = book.createSheet();
			if (CollectionUtils.isNotEmpty(data)) {
				int i = 0;
				for (Map<Integer, String> map : data) {
					Row row = sheet.createRow(i);
					if (MapUtils.isNotEmpty(map)) {
						for (Integer key : map.keySet()) {
							Cell cell = row.createCell(key);
							cell.setCellValue(map.get(key));
						}
					}
					i++;
				}
			}
			book.write(os);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (book != null)
				book.close();
		}
	}

	public static final List<Map<Integer, String>> readExcel(String excelType, InputStream is, int maxRow) throws Exception {
		if (!excel2003.equalsIgnoreCase(excelType) && !excel2007.equalsIgnoreCase(excelType)) {
			throw new BaseException(C.EMPTY, "格式不正确");
		}
		List<Map<Integer, String>> dataList = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> dataMap = null;

		Workbook book = null;
		Sheet sheet = null;
		Row row = null;
		boolean isValidRow = false;
		if (excel2003.equalsIgnoreCase(excelType)) {
			book = new HSSFWorkbook(is);
		} else if (excel2007.equalsIgnoreCase(excelType)) {
			book = new XSSFWorkbook(is);
		}
		try {
			sheet = book.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() > maxRow) {
				throw new BaseException(C.EMPTY, "最多只能导入" + maxRow + "条数据");
			}

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				isValidRow = false;
				row = sheet.getRow(i);
				if (row != null) {
					dataMap = new HashMap<Integer, String>();
					for (int j = 0; j < sheet.getRow(0).getPhysicalNumberOfCells(); j++) {
						if (row.getCell(j) == null) {
							dataMap.put(j, C.EMPTY);
						} else {
							FormulaEvaluator e = book.getCreationHelper().createFormulaEvaluator();
							String content = parseExcel(e.evaluateInCell(row.getCell(j)));
							if (!StringUtils.isEmpty(content)) {
								isValidRow = true;
							}
							dataMap.put(j, content);
						}
					}
					if (isValidRow) {
						dataList.add(dataMap);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (book != null)
				book.close();
		}
		return dataList;
	}

	/**
	 * excel单元格格式内容获取
	 * 
	 * @param cell
	 * @return
	 */
	private static final String parseExcel(Cell cell) {
		String result = new String();
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				result = sdf.format(date);
			} else {
				BigDecimal value = new BigDecimal(cell.getNumericCellValue());
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String dataFormatStr = style.getDataFormatString();
				// 单元格设置成常规
				if ("General".equals(dataFormatStr)) {
					format.applyPattern("#");
				}
				BigDecimal f1 = value.setScale(2, BigDecimal.ROUND_HALF_UP);
				result = f1 + C.EMPTY;
				String nums[] = result.split("\\.");
				if (nums.length == 2) {
					if ("00".equals(nums[1])) {
						result = nums[0];
					}
				}
			}
			break;
		case STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			break;
		case FORMULA:// 公式类型
			// 此处判断使用公式生成的字符串有问题，因为HSSFDateUtil.isCellDateFormatted(cell)判断过程中cell.getNumericCellValue();方法会抛出java.lang.NumberFormatException异常
			try {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					result = sdf.format(date);
				} else {
					result = String.valueOf(cell.getNumericCellValue());
				}
			} catch (IllegalStateException e) {
				result = String.valueOf(cell.getRichStringCellValue());
			}
			break;
		case BLANK:// 空白
			result = C.EMPTY;
			break;
		case BOOLEAN:// boolean类型
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		default:
			result = C.EMPTY;
			break;
		}
		return result;
	}

}
