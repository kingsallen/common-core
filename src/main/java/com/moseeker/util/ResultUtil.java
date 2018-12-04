package com.moseeker.util;

import com.moseeker.dto.ResultList;
import com.moseeker.dto.ResultMap;
import com.moseeker.dto.ResultObject;
import com.moseeker.dto.ResultPagination;
import com.moseeker.enums.CommonExceptionEnum;
import com.moseeker.exception.BaseException;
import com.moseeker.vo.Pagination;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public final class ResultUtil {

	public static <T> List<T> getDataFromResult(ResultList<T> resultList) throws BaseException {
		if (resultList == null) {
			return null;
		}
		if (!resultList.getCode().equalsIgnoreCase(CommonExceptionEnum.success.getKey())) {
			throw new BaseException(resultList.getCode(), resultList.getMessage());
		}
		return resultList.getData();
	}

	public static <T> Map<String, T> getDataFromResult(ResultMap<T> resultMap) throws BaseException {
		if (resultMap == null) {
			return null;
		}
		if (!resultMap.getCode().equalsIgnoreCase(CommonExceptionEnum.success.getKey())) {
			throw new BaseException(resultMap.getCode(), resultMap.getMessage());
		}
		return resultMap.getData();
	}

	public static <T> T getDataFromResult(ResultObject<T> resultObject) throws BaseException {
		if (resultObject == null) {
			return null;
		}
		if (!resultObject.getCode().equalsIgnoreCase(CommonExceptionEnum.success.getKey())) {
			throw new BaseException(resultObject.getCode(), resultObject.getMessage());
		}
		return resultObject.getData();
	}

	public static <T> Pagination<T> getDataFromResult(ResultPagination<T> resultPagination) throws BaseException {
		if (resultPagination == null) {
			return null;
		}
		if (!resultPagination.getCode().equalsIgnoreCase(CommonExceptionEnum.success.getKey())) {
			throw new BaseException(resultPagination.getCode(), resultPagination.getMessage());
		}
		return resultPagination.getData();
	}

}
