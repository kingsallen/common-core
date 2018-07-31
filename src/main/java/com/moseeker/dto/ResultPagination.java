package com.moseeker.dto;

import com.moseeker.vo.Pagination;

import lombok.Data;

@Data
public class ResultPagination<T> {
	
	private String code;
	private String message;
	private Pagination<T> data;

}
