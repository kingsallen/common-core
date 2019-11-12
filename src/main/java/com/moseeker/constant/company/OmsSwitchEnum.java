package com.moseeker.constant.company;

import com.moseeker.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author Rays
 * @DATE 2019-08-30
 **/
public enum OmsSwitchEnum {

    /**
     * 不存在的开关
     */
    NONE(0,"无", false),
    IM_EMPLOYEE(1,"我是员工", false),
    FANS_RECOMMEND(2,"粉丝智能推荐", false),
    MEET_MOBOT(3,"meet mobot", false),
    EMPLOYEE_RECOMMEND(4,"员工智能推荐", false),
    SOCIAL_RECRUIT(5,"社招", false),
    ONCAMPUS_RECRUIT(6,"校招", false),
    SOCIAL_RADAR(7,"人脉雷达", false),
    REHIRE(8,"老员工回聘", false),
    FORTUNE500(9,"五百强", false),
    MULTI_IP_VISIT(10,"多IP访问", false), //
    REDPACKAGE_ACTIVITY(11,"红包活动", false),
    ATS_RECRUIT_PROCESS_UPGRADE(12,"ATS招聘流程升级", false),
    HUNTER_MANAGE(13,"猎头管理", false),
    WORK_WEICHAT(14,"企业微信版", false),// 允许使用企业微信进行员工认证
    IDCARD_RECOGNITION(15,"身份证识别", false),//身份证识别
    LBS_POSITION_LIST(16,"LBS职位列表",false),//LBS职位列表
    MOMO_WECHAT(17,"MOMO微信端", true),//身份证识别
    EMPLOYEE_IM_CHAT(30,"员工IM聊天",true); // 员工IM聊天 默认开

    private int value;
    private String name;
    private boolean valid;

    private static Map<Integer, OmsSwitchEnum> map = new HashMap<>();

    static {
        for (OmsSwitchEnum csat : values()) {
            map.put(csat.getValue(), csat);
        }
    }

    OmsSwitchEnum(int value, String name, boolean valid) {
        this.value = value;
        this.name = name;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    /**
     * 返回是否可用对应的byte值。可用返回1，不可用返回0
     * @return 是否可用对应的byte类型的值
     */
    public byte getValidToByte() {
        return (byte) (valid?1:0);
    }

    public boolean getValid() {
        return valid;
    }


    @Override
    public String toString() {
        return name;
    }

    /**
     * TODO: 既然存在None了，返回值就不应该是null
     * @param value
     * @return
     */
    public static OmsSwitchEnum instanceFromValue(Integer value) {
        if (value !=null && map.get(value) != null) {
            return map.get(value);
        }else{
            return null ;
        }
    }

    /**
     * TODO: 既然存在None了，返回值就不应该是null
     * @param name
     * @return
     */
    public static OmsSwitchEnum instanceFromName(String name) {
        if (StringUtils.isNotNullOrEmpty(name)) {
            for(Integer key: map.keySet()){
                if(map.get(key).getName().equals(name)){
                    return map.get(key);
                }
            }
        }
        return null ;
    }

    public static boolean isValid(Byte valid){
        return Byte.valueOf((byte)1).equals(valid);
    }
}
