package com.moseeker.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.moseeker.exception.BaseException;

public final class ProjectUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean strIsNull(String str) {
		if (str == null || C.EMPTY.equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象不是null或空字符串
	 * 
	 * @param o
	 * @return
	 */
	public static final boolean objectIsNotEmpty(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof String || o instanceof StringBuilder || o instanceof StringBuffer) {
			return o != null && !StringUtils.isEmpty(o.toString());
		}
		return o != null;
	}

	/**
	 * null返回""
	 * 
	 * @param str
	 * @return
	 */
	public static final String convertNullToEmpty(Object str) {
		if (str == null) {
			return C.EMPTY;
		}
		return str.toString();
	}

	/**
	 * null返回0
	 * 
	 * @param source
	 * @return
	 */
	public static final BigDecimal convertNullToZero(BigDecimal source) {
		if (source == null) {
			return BigDecimal.ZERO;
		}
		return source;
	}

	/**
	 * 生成随机的验证码(手机短信验证码)
	 * 
	 * @return
	 */
	public static final String genValidationCode() {
		int a1 = (int) (Math.random() * (10 - 1 + 1));
		int a2 = (int) (Math.random() * (10 - 1 + 1));
		int a3 = (int) (Math.random() * (10 - 1 + 1));
		int a4 = (int) (Math.random() * (10 - 1 + 1));
		int a5 = (int) (Math.random() * (10 - 1 + 1));
		int a6 = (int) (Math.random() * (10 - 1 + 1));
		return C.EMPTY + a1 + a2 + a3 + a4 + a5 + a6;
	}

	/**
	 * 计算两个经纬度之间的距离
	 * 
	 * @param y1
	 * @param x1
	 * @param y2
	 * @param x2
	 * @return
	 */
	public static final String GetDistance(double y1, double x1, double y2, double x2) {
		double radLat1 = y1 * Math.PI / 180.0;
		double radLat2 = y2 * Math.PI / 180.0;
		double a = radLat1 - radLat2;
		double b = x1 * Math.PI / 180.0 - x2 * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * C.EARTH_RADIUS * 1000;
		long result = Math.round(s);
		DecimalFormat dfa = new DecimalFormat("#####0");
		return dfa.format(result);
	}

	public static final String getErrorMessage(Exception e) {
		return getErrorMessage(e, "操作失败。");
	}

	/**
	 * 获取异常的错误消息
	 *
	 * @param e
	 * @return
	 */
	public static final String getErrorMessage(Exception e, String msgPrefix) {
		if (e == null) {
			return msgPrefix;
		}
		String msgContent = null;
		if (e instanceof BaseException) {
			BaseException base = (BaseException) e;
			msgContent = base.getErrorMessage();
		} else if (e.getCause() == null) {
			msgContent = e.getMessage();
		} else {
			msgContent = e.getCause().getMessage();
		}
		if (msgContent == null) {
			return msgPrefix;
		}
		String other = "The data is updated by others";
		if (msgContent.contains(other)) {
			msgContent = "数据已被更新，请刷新";
		}
		String notFound = "No data found";
		if (msgContent.contains(notFound)) {
			msgContent = "找不到数据";
		}
		String noInsert = "No row is inserted";
		if (msgContent.contains(noInsert)) {
			msgContent = "没有数据被插入";
		}
		String noUpdate = "No row is updated";
		if (msgContent.contains(noUpdate)) {
			msgContent = "没有数据被更新";
		}
		String noDelete = "No row is deleted";
		if (msgContent.contains(noDelete)) {
			msgContent = "没有数据被删除";
		}
		String createByNull = "create_by is null";
		if (msgContent.contains(createByNull)) {
			msgContent = "创建人ID错误";
		}
		String udpateByNull = "update_by is null";
		if (msgContent.contains(udpateByNull)) {
			msgContent = "修改人ID错误";
		}
		return msgPrefix + msgContent;
	}

	/**
	 * 将异常堆栈信息转换为字符串
	 *
	 * @param e
	 * @return
	 */
	public static final String exceptionStack2String(Exception e) {
		if (e != null) {
			ByteArrayOutputStream traceOutputStream = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(traceOutputStream));
			try {
				return new String(traceOutputStream.toByteArray(), "UTF8");
			} catch (UnsupportedEncodingException e1) {
				return new String(traceOutputStream.toByteArray());
			}
		}
		return null;
	}

	/**
	 * 控制台打印异常信息，自定义异常不打印堆栈
	 * 
	 * @param e
	 */
	public static final void printStackTrace(Exception e) {
		if (e == null) {
			System.out.println("Exception e is null");
			return;
		}
		if (e instanceof BaseException) {
			System.out.println(ProjectUtil.getErrorMessage(e));
		} else {
			e.printStackTrace();
		}
	}

	/**
	 * List转String
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static final String listToString(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(separator);
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * String转List
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static final List<String> stringToList(String str, String separator) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		for (String s : str.split(separator)) {
			list.add(s);
		}
		return list;
	}

	/**
	 * 获取文件的MD5
	 * 
	 * @param fis
	 * @return
	 */
	public static final String getFileMd5(InputStream fis) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, length);
			}
			BigInteger bigInt = new BigInteger(1, md.digest());
			md5 = bigInt.toString(16).toUpperCase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return md5;
	}

	/**
	 * 连接2个url
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static final String appendUrl(String first, String second) {
		if (StringUtils.isEmpty(first)) {
			return second;
		}
		if (StringUtils.isEmpty(second)) {
			return first;
		}
		// 两个衔接的均有斜杠
		if (first.endsWith("/") && second.startsWith("/")) {
			return first + second.substring(1);
		}
		// 两个衔接的均没有斜杠
		if (!first.endsWith("/") && !second.startsWith("/")) {
			return first + "/" + second;
		}
		return first + second;
	}

	/**
	 * 获取图片压缩url
	 * 
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 */
	public static final String getImageCompressUrl(String url, Integer type, Integer width, Integer height) {
		if (StringUtils.isEmpty(url)) {
			return url;
		}
		// 七牛CDN图片压缩
		if (StringUtils.startsWithIgnoreCase(url, "https://img.jiaw.com")) {
			String param = C.EMPTY;
			if (width != null && width > 0) {
				param = param + "/w/" + width;
			}
			if (height != null && height > 0) {
				param = param + "/h/" + height;
			}
			return url + "?imageView2/" + type + param;
		}
		return url;
	}

	public static final String getConsolePrefix() {
		return "[" + DateUtil.format(DateUtil.currentTime(), C.FORMAT_YYYYMMDD_HHMISS_SSS) + "]";
	}

}
