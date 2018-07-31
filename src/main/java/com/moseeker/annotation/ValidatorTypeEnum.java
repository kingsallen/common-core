package com.moseeker.annotation;

public enum ValidatorTypeEnum {

	NULL(null), //
	IP("ip"), //
	URL("url"), //
	EMAIL("email"), //
	MOBILE("mobile"),//
	;

	private String v;

	private ValidatorTypeEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
