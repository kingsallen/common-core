package com.moseeker.enums;

public enum SmsExceptionEnum implements BaseEnum<SmsExceptionEnum> {
	SMS_COMMON_ERROR("SM80000","短信发送异常"),
	USER_SMS_LIMITED("SM80001", "短信发送异常"), //
	;

	private String key;
	private String value;

	private SmsExceptionEnum(String key, String value) {
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
