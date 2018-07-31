package com.moseeker.vo;

import java.io.Serializable;
import java.util.Date;

import com.moseeker.util.C;
import com.moseeker.util.DateUtil;

import lombok.Data;

@Data
public class BaseVo implements Serializable {

	private static final long serialVersionUID = 2010592892081877918L;
	private Long id;
	private String remark;
	private String deleted;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private Integer version;

	public String getCreateTimeStr() {
		Date ct = getCreateTime();
		if (ct == null) {
			return C.EMPTY;
		}
		return DateUtil.format(ct, C.FORMAT_YYYYMMDD_HHMISS);
	}

	public String getCreateDateStr() {
		Date ct = getCreateTime();
		if (ct == null) {
			return C.EMPTY;
		}
		return DateUtil.format(ct, C.FORMAT_YYYYMMDD);
	}

	public String getUpdateTimeStr() {
		Date ct = getUpdateTime();
		if (ct == null) {
			return C.EMPTY;
		}
		return DateUtil.format(ct, C.FORMAT_YYYYMMDD_HHMISS);
	}

	public String getUpdateDateStr() {
		Date ct = getCreateTime();
		if (ct == null) {
			return C.EMPTY;
		}
		return DateUtil.format(ct, C.FORMAT_YYYYMMDD);
	}
	
}
