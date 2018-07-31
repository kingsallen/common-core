package com.moseeker.annotation;

public enum ColumnCharsetEnum {

	utf8("utf8");

	private String v;

	private ColumnCharsetEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
