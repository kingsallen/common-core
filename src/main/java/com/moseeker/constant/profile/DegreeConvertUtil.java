package com.moseeker.constant.profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zztaiwll on 18/4/25.
 */
public enum DegreeConvertUtil {

        NOCHOICE(0, "未选择"),
        MIDDLESCHOOL(1, "初中及以下"),
        SECONDARY(2, "中专"),
        HIGHTSCHOOL(3, "高中"),
        JUNIORCOLLEGE(4, "大专"),
        UNDERGRADUATE(5, "本科"),
        MASTER(6, "硕士"),
        DOCTOR(7, "博士"),
        POSTDOCTOR(8, "博士以上"),
        OTHER(9, "其他");

        private int value;
        private String degree;
        DegreeConvertUtil(int value, String degree) {
            this.value = value;
            this.degree = degree;
        }

        public static final Map<Integer,String > intToEnum = new HashMap();

        static {
            for (DegreeConvertUtil op : values())
                intToEnum.put(op.getValue(),op.getDegree());
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }
    }

