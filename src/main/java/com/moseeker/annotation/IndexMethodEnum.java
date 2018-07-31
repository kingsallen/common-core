package com.moseeker.annotation;

public enum IndexMethodEnum {

	BTREE("BTREE"), HASH("HASH");

	private String v;

	private IndexMethodEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
