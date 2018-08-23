package com.moseeker.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@Deprecated
public class BaseDto implements Serializable {
	
	private static final long serialVersionUID = 6705330896059224050L;
	
	private Long id;
	private String remark;
	private String deleted;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private Integer version;

}
