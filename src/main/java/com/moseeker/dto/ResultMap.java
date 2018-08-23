package com.moseeker.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ResultMap {
	
	private String code;
	private String message;
	private Map<String, Object> data;

}
