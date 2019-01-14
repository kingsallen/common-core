package com.moseeker.dto;

import com.moseeker.enums.CommonExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultList<T> {
	
	private String code;
	private String message;
	private List<T> data;
	
	public static <T> ResultList<T> getSuccessResultList(List<T> list) {
		return new ResultList<T>(CommonExceptionEnum.success.getKey(), CommonExceptionEnum.success.getValue(), list);
	}

	public static <T> ResultList<T> getErrorResultList(String key, String value,List<T> list) {
		return new ResultList<T>(key, value, list);
	}
}
