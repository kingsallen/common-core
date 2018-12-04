package com.moseeker.vo;

import com.moseeker.constant.Constant;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Deprecated
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = -6922440751113440798L;
	private int allSize;// 总共多少条
	private int allPage;// 总共多少页
	private int pageSize = Constant.DEFAULT_PAGE_SIZE;// 每页大小
	private int pageIndex = Constant.DEFAULT_PAGE_INDEX;// 当前第几页
	private List<T> results;

	public void setPagination(int allSize, int allPage, int pageSize, int pageIndex) {
		this.allSize = allSize;
		this.allPage = allPage;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	@Deprecated
	public void copyPagination(Pagination<?> page) {
		this.allSize = page.getAllSize();
		this.allPage = page.getAllPage();
		this.pageSize = page.getPageSize();
		this.pageIndex = page.getPageIndex();
	}

	public void setPagination(Pagination<?> page) {
		this.allSize = page.getAllSize();
		this.allPage = page.getAllPage();
		this.pageSize = page.getPageSize();
		this.pageIndex = page.getPageIndex();
	}

}
