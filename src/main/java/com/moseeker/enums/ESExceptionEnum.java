package com.moseeker.enums;

public enum ESExceptionEnum implements BaseEnum<ESExceptionEnum> {
    timeOut("es10001","es连接异常");
    private String key;
    private String value;

    private ESExceptionEnum(String key, String value) {
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
