package com.moseeker.annotation;

public enum TableEngineEnum {

	InnoDB("InnoDB");

	private String v;

	private TableEngineEnum(String v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return v;
	}

}
