package com.moseeker.dto;

import com.moseeker.enums.CommonExceptionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
	
	private String code;
	private String message;
	
	public static Result getSuccessResult() {
		return new Result(CommonExceptionEnum.success.getKey(), CommonExceptionEnum.success.getValue());
	}

}
