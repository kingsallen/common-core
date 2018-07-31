package com.moseeker.enums;

public enum CommonDeletedEnum implements BaseEnum<CommonDeletedEnum> {

	yes("yes", "是"), //
	no("no", "否"),//
	;

	private String key;
	private String value;

	private CommonDeletedEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

}
