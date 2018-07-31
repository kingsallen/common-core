package com.moseeker.annotation;

public enum TableCharsetEnum {

	utf8("utf8");

	private String v;

	private TableCharsetEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
