package com.moseeker.dto;

import com.moseeker.enums.CommonExceptionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject<T> {
	
	private String code;
	private String message;
	private T data;

	public static <T> ResultObject<T> getSuccessResultObject(T object) {
		return new ResultObject<T>(CommonExceptionEnum.success.getKey(), CommonExceptionEnum.success.getValue(), object);
	}
	
}
