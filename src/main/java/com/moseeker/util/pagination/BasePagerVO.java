package com.moseeker.util.pagination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "分页查询数据")
public  class BasePagerVO<T> {

    @ApiModelProperty(value = "数据总数（一共多少数量）",required = false)
    protected Integer totalRows;

    @ApiModelProperty(value = "页面总数(可以分为多少页，此数据不能设置)",required = false)
    protected  Integer totalPages;

    @ApiModelProperty(value = "每页数量",required = false)
    protected Integer pageSize;

    @ApiModelProperty(value = "当前页号",required = false)
    protected Integer currentPage;

    @ApiModelProperty(value = "当前页的数据汇总",required = false)
    protected List<T> currentPageData;



    public BasePagerVO(BasePagerReqVO req) {

        this.setCurrentPage(req.getCurrentPage());
        this.setPageSize(req.getPageSize());
    }

    public BasePagerVO(){
    }

    private void calculateTotalPages() {
        if ( this.totalRows ==null || this.totalRows == 0 ){
            this.totalPages= 0;
        }

        this.totalPages = Double.valueOf (Math.ceil( this.totalRows / this.pageSize)).intValue();
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) throws Exception {
        if ( this.getPageSize() == null || this.getPageSize()<1){
            throw new Exception("pageSize必须大于0！");
        }

        if (this.getCurrentPage() == null || this.getCurrentPage()<1 ){
            throw new Exception("currentPage必须大于0！");
        }

        if (totalRows == null || totalRows<0){
            throw new Exception("总数不能小于0！");
        }

        this.totalRows = totalRows;

        this.calculateTotalPages();

        // 修正currentPage
        if (this.totalRows == 0){
            this.setCurrentPage(1);
        }
        if ( (this.totalRows>0) && (this.getCurrentPage() > this.getTotalPages()) ){
            this.setCurrentPage(this.getTotalPages());
        }


    }

    public Integer getTotalPages() {
        this.calculateTotalPages();
        return totalPages;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getCurrentPageData() {
        return currentPageData;
    }

    public void setCurrentPageData(List<T> currentPageData) {
        this.currentPageData = currentPageData;
    }
}
