package com.moseeker.enums;

public enum CommonExceptionEnum implements BaseEnum<CommonExceptionEnum> {

	success("0", "操作成功"), //
	error10001("10001", "系统异常"),//
	error10002("10002", "服务不可用"),//
	error10003("10003", "未知错误"),//
	error10004("10004", "端口错误"),//
    error10005("10005", "缺少必要地校验参数！"),//
    error10006("10006", "还未校验"),//
	error10007("10007", "请求参数错误！"),//
	error10008("10008", "appid为空"),//
	error10009("10009", "appid非法"),//
	error10010("10010", "company数据不存在"),//
	;

	private String key;
	private String value;

	private CommonExceptionEnum(String key, String value) {
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
