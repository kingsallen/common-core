package com.moseeker.constant.profile;

import java.util.HashMap;
import java.util.Map;

public enum SkillSource {

    NOVALUES(0, "未填写"),
    NORMAL(1, "了解", "一般", "略懂"),
    MEDIUM(2, "掌握", "良好", "中级", "中等"),
    SKILLED(3, "熟练"),
    MASTER(4, "精通");

    private int value;
    private String [] levelName;

    SkillSource(int value, String... levelName) {
        this.value = value;
        this.levelName = levelName;
    }

    public static final Map<String, Integer> intToEnum = new HashMap<>();

    static {
        for (SkillSource ss : values()) {
            for (String levelName : ss.getLevelName()) {
                intToEnum.put(levelName, ss.value);
            }
        }
    }

    public static int get(String levelName) {
        if (intToEnum.containsKey(levelName)) {
            return intToEnum.get(levelName);
        }
        return NOVALUES.value;
    }

    public String[] getLevelName() {
        return levelName;
    }
}
