package com.moseeker.annotation;

public enum ColumnCollateEnum {

	utf8_general_ci("utf8_general_ci");

	private String v;

	private ColumnCollateEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
