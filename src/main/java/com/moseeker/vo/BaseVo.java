package com.moseeker.vo;

import com.moseeker.constant.Constant;
import com.moseeker.util.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Deprecated
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
			return Constant.EMPTY;
		}
		return DateUtil.format(ct, Constant.FORMAT_YYYYMMDD_HHMISS);
	}

	public String getCreateDateStr() {
		Date ct = getCreateTime();
		if (ct == null) {
			return Constant.EMPTY;
		}
		return DateUtil.format(ct, Constant.FORMAT_YYYYMMDD);
	}

	public String getUpdateTimeStr() {
		Date ct = getUpdateTime();
		if (ct == null) {
			return Constant.EMPTY;
		}
		return DateUtil.format(ct, Constant.FORMAT_YYYYMMDD_HHMISS);
	}

	public String getUpdateDateStr() {
		Date ct = getCreateTime();
		if (ct == null) {
			return Constant.EMPTY;
		}
		return DateUtil.format(ct, Constant.FORMAT_YYYYMMDD);
	}
	
}
