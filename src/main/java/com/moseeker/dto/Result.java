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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
