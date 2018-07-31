package com.moseeker.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasePagerVo<T> extends BaseVo {

	private static final long serialVersionUID = 6156744855563002359L;

	private Pagination<T> pager = new Pagination<T>();

}
