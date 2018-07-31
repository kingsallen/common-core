package com.moseeker.enums;

public enum CommonOnOffEnum implements BaseEnum<CommonOnOffEnum> {

	on("on", "开启"), //
	off("off", "关闭"),//
	;

	private String key;
	private String value;

	private CommonOnOffEnum(String key, String value) {
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
