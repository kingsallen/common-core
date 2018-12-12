package com.moseeker.enums;

public enum KeyIdentifier {
    DEFAULT,
    LOG,
    LOG_INPROGRESS,
    SMS_SIGNUP,
    SMS_PWD_FORGOT,
    SMS_VERIFY_MOBILE,
    DICT_CITY,
    SMS_CHANGEMOBILE_CODE,
    SMS_RESETMOBILE_CODE,
    DICT_COLLEGE,
    APPLICATION_COUNT_CHECK,
    // 限制同一职位，一个候选人只能申请一次
    APPLICATION_SINGLETON,
    HR_SMS_SIGNUP,
    MQ_MESSAGE_NOTICE_TEMPLATE,
    MQ_MESSAGE_NOTICE_TEMPLATE_INPRO,
    MQ_MESSAGE_EMAIL_BIZ,
    MQ_MESSAGE_EMAIL_WARNING,
    WEIXIN_SCANRESULT,
    NEWSLETTER_HRACCOUNT_READED,
    NEW_WARNING_REDIS_KEY,
    MQ_MESSAGE_NOTICE_TEMPLATE_DELAY,
    MQ_MESSAGE_NOTICE_VERIFY_EMAIL,
    THIRD_PARTY_CITY,
    THIRD_PARTY_POSITION_SYNCHRONIZATION_QUEUE,
    THIRD_PARTY_POSITION_SYNCHRONIZATION_COMPLETED_QUEUE,
    THIRD_PARTY_POSITION_REFRESH,
    THIRD_PARTY_POSITION_REFRESH_QUEUE,
    THIRD_PARTY_POSITION_REFRESH_COMPLETED_QUEUE,
    THIRD_PARTY_ACCOUNT_COMPLETE_QUEUE,
    THIRD_PARTY_ACCOUNT_BINDING,
    REFRESH_THIRD_PARTY_PARAM,
    THIRD_PARTY_ENVIRON_PARAM,
    SYNC_THIRD_PARTY_POSITION,
    POSITION_SYNC_VERIFY_TIMEOUT,
    TALENTPOOL_PROFILE_FILTER_ADD,
    TALENTPOOL_COMPANY_TAG_ADD,
    TALENTPOOL_HR_AUTOMATIC_TAG_ADD,
    ES_UPDATE_INDEX_COMPANYTAG_ID,
    ES_UPDATE_INDEX_HR_AUTO_ID,
    COMPANY_TAG_ES_STATUS,
    HR_AUTOMATIC_TAG_ES_STATUS,
    PAST_USER_EMPLOYEE_VALIDATE,
    LAST_SEND_POSITION_INVITE,
    LIEPIN_TOKEN_REFRESH,
    DICT_COLLEGE_COUNTRY,
    DICT_COLLEGE_HAT,
    DICT_COLLEGE_CHINA,
    USER_POSITION_SEARCH,
    EMPLOYEE_REFERRAL_PROFILE,
    EMPLOYEE_REFERRAL_PROFILE_TYPE,
    POSITION_PC_REPORT,
    WECHAT_UPLOAD_RESUME_FILE,
    USER_EMPLOYEE_DELETE,
    USER_EMPLOYEE_UNBIND,
    REDIS_KEY_APPLICATION_COUNT_CHECK
}
