package com.moseeker.constant.jobboard;

public enum SyncRequestType {
    /**
     * 调用端：HR
     */
    WEB(1,"网页端"),
    /**
     * 调用端：ATS一段式对接
     */
    ATS(2,"ATS");

    SyncRequestType(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public static boolean hasType(int code){
        return getInstance(code)!=null;
    }

    public static SyncRequestType getInstance(int code){
        for(SyncRequestType requestType:values()){
            if(requestType.code==code){
                return requestType;
            }
        }
        return null;
    }

    private int code;
    private String title;

    public int code(){
        return code;
    }
    public String title(){
        return title;
    }
}
