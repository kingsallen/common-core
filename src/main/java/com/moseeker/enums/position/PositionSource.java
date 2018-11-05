package com.moseeker.enums.position;

public enum PositionSource {
    MANUAL(0),
    IMPORT(1),
    ATS(9)
    ;

    PositionSource(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
