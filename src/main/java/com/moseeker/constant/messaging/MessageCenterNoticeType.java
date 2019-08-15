package com.moseeker.constant.messaging;

/**
 * 消息中心接收消息类型
 */
public enum MessageCenterNoticeType {

    /**
     * 提醒-@我的
     */
    ATTENTION(3),

    /**
     * 推荐-内推投递
     */
    RECOMMAND_BY_EMPLOYEE(8),

    /**
     * 推荐-HR推荐
     */
    RECOMMAND_BY_HR(9),

    /**
     * 推荐-猎头推荐
     */
    RECOMMAND_BY_HEADHUNTER(10),

    /**
     * 面试-安排面试
     */
    INTERVIEW_ASSIGN(11),

    /**
     * 面试-面试反馈
     */
    INTERVIEW_FEEDBACK(12),

    /**
     * 面试-候选人未参面
     */
    CANDIDATE_NOT_COME(13),

    /**
     * 面试-取消面试
     */
    INTERVIEW_CANCEL(14),

    /**
     *
     */
    INTERVIEW_PASSED(15),

    /**
     * 入职-候选人入职
     */
    ENTRY(16),

    /**
     * 其他-猎头服务期限即将到期
     */
    OTHER_HEADHUNTER_SERVICE_EXPIRE(17),

    /**
     * 其他-服务合同到期
     */
    OTHER_CONSTRACT_EXPIRE(18),

    /**
     * 其他-淘汰
     */
    OTHER_FAILURE(19),

    /**
     * 其他-候选人备注
     */
    OTHER_CANDIDATE_REMARK(20)
    ;


    private int messageType;

    MessageCenterNoticeType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }}
