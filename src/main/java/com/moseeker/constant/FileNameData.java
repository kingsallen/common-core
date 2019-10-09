package com.moseeker.constant;

/**
 * @Description TODO
 * @Author Rays
 * @DATE 2019-09-03
 **/
public class FileNameData {

    private String fileName;
    private String fileAbsoluteName;
    private String originName;
    private String saveUrl;

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAbsoluteName() {
        return fileAbsoluteName;
    }

    public void setFileAbsoluteName(String fileAbsoluteName) {
        this.fileAbsoluteName = fileAbsoluteName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}
