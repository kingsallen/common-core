package com.moseeker.dto;

import com.moseeker.enums.CommonExceptionEnum;
import com.moseeker.vo.Pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultPagination<T> {
	
	private String code;
	private String message;
	private Pagination<T> data;
	
	public static <T> ResultPagination<T> getSuccessResultPagination(Pagination<T> pagination) {
		return new ResultPagination<T>(CommonExceptionEnum.success.getKey(), CommonExceptionEnum.success.getValue(), pagination);
	}
	
}
