package com.moseeker.constant.parsing;

import com.moseeker.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum  FileTypeEnum {

    pdf(".PDF"),txt(".TXT"),doc(".DOC"),docx(".DOCX"),xml(".XML"),html(".HTML"),jpg(".JPG"),png(".PNG");

    private String fileType;
    private static Map<String, FileTypeEnum> map = new HashMap<>();

    static {
        for (FileTypeEnum csat : values()) {
            map.put(csat.getFileType(), csat);
        }
    }

    public static Map<String, FileTypeEnum> getMap() {
        return map;
    }

    public static void setMap(Map<String, FileTypeEnum> map) {
        FileTypeEnum.map = map;
    }

    FileTypeEnum(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public static FileTypeEnum instanceFromType(String type) {
        if (StringUtils.isNotNullOrEmpty(type) && map.get(type) != null) {
            return map.get(type);
        }
        return null;
    }
}
