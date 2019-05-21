package com.moseeker.constant.profile;

import com.moseeker.constant.application.AppOriginEnum;

import java.util.HashMap;
import java.util.Map;

public enum ProfileOriginEnum {
    ORIGIN_51_UPLOAD(20,"前程无忧上传"),
    ORIGIN_ZHILIAN(21,"智联上传"),
    ORIGIN_LIEPIN(22,"猎聘上传"),
    ORIGIN_ZUIJIA(23,"最佳东方上传"),
    ORIGIN_YILAN(24,"一览英才上传"),
    ORIGIN_PROFILE(25,"简历上传"),
    ORIGIN_JOBSDB(26,"JobsDB上传"),
    ORIGIN_MINHANG(27,"民航上传"),
    ORIGIN_NEW_GRADUATION(28,"应届生（上传）"),
    ORIGIN_EMPLOYEE_SEND(29,"员工主动上传 "),
    ORIGIN_EMPLOYEE_RECOM(30,"内推"),
    ORIGIN_51_SEND(31,"前程无忧（主投导入）"),
    ORIGIN_51_DOWNLOAD(32,"前程无忧（下载导入）"),
    ORIGIN_ZHILIAN_SEND(33,"智联招聘（主投导入）"),
    ORIGIN_ZHILIAN_DOWNLOAD(34,"智联招聘（下载导入）"),
    ORIGIN_58(35,"58同城上传");

    private int length;
    private String name;
    private ProfileOriginEnum(int length,String name){
        this.length=length;
        this.name=name;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
    public static Map<Integer, String> map = new HashMap<>();
    static {
        for (ProfileOriginEnum profileOriginEnum : ProfileOriginEnum.values()){
            map.put(profileOriginEnum.getLength(), profileOriginEnum.getName());
        }
    }
}
