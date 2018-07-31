package com.moseeker.dto;

import lombok.Data;

@Data
public class ResultObject<T> {
	
	private String code;
	private String message;
	private T data;

}
