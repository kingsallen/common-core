package com.moseeker.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResultList<T> {
	
	private String code;
	private String message;
	private List<T> data;

}
