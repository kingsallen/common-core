package com.moseeker.util.pagination;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasePagerReqVO<Query> {
    @ApiModelProperty(value = "每页数量",required = true)
    protected Integer pageSize;

    @ApiModelProperty(value = "请求第几页",required = true)
    protected Integer currentPage;

    @ApiModelProperty(value = "搜索条件",required = false)
    Query query;
}
