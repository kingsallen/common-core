package com.moseeker.constant.messaging;

/**
 * 消息中心接收消息类型
 */
public enum MessageCenterNoticeType {

    RECOMMAND_BY_EMPLOYEE("推荐-内推投递",1),
    RECOMMAND_BY_HR("推荐-HR推荐",2),
    RECOMMAND_BY_HEADHUNTER("推荐-猎头推荐",3),
    INTERVIEW_ASSIGN("面试-安排面试",4),
    INTERVIEW_FEEDBACK("面试-面试反馈",5),
    INTERVIEW_CANCEL("面试-取消面试",7),
    ENTRY("入职-候选人入职",9),
    OTHER_HEADHUNTER_SERVICE_EXPIRE("其他-猎头服务期限即将到期",10),
    OTHER_CONSTRACT_EXPIRE("其他-服务合同到期",11),
    OTHER_FAILURE("其他-淘汰",12),
    OTHER_CANDIDATE_REMARK("其他-候选人备注",13),
    ATTENTION("提醒-@我的",14)
    ;


    private String desc;

    private int messageType;

    MessageCenterNoticeType(String desc, int messageType) {
        this.desc = desc;
        this.messageType = messageType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }}
