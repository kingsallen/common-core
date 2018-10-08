package com.moseeker.dto;

import java.util.Map;

import com.moseeker.enums.CommonExceptionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMap<T> {
	
	private String code;
	private String message;
	private Map<String, T> data;

	public static <T> ResultMap<T> getSuccessResultMap(Map<String, T> map) {
		return new ResultMap<T>(CommonExceptionEnum.success.getKey(), CommonExceptionEnum.success.getValue(), map);
	}
	
}
