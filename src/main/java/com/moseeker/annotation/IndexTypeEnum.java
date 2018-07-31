package com.moseeker.annotation;

public enum IndexTypeEnum {

	Normal("Normal"), Unique("Unique"), FullText("Full Text");

	private String v;

	private IndexTypeEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
