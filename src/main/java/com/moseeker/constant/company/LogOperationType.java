package com.moseeker.constant.company;

/**
 * 操作记录的操作类型（增删改）
 * @author yehu
 */
public enum LogOperationType {
    /**
     * 新增
     */
    CREATE(1),

    /**
     * 删除
     */
    DELETE(2),

    /**
     * 修改
     */
    UPDATE(3),

    /**
     * 默认或未知
     */
    DEFAULT(0);

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    LogOperationType(Integer num) {
        this.num = num;
    }
}
