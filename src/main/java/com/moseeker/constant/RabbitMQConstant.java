package com.moseeker.constant;/**
 * Created by zztaiwll on 18/11/30.
 */

/**
 * @version 1.0
 * @className RabbitMQConstant
 * @Description TODO
 * @Author zztaiwll
 * @DATE 18/11/30 上午10:45
 **/
public enum RabbitMQConstant {
    POSITION_QUEUE_RESYNC("position_que_resync"),
    POSITION_QUEUE_DOWNSHELF("position_que_downshelf"),
    POSITION_QUEUE_DELETE("position_que_delete"),
    POSITION_QUEUE_EDIT("position_que_edit"),
    POSITION_OPERATE_LIEPIN_EXCHANGE("position_operate_liepin_exchange"),
    POSITION_OPERATE_ROUTEKEY_EDIT("position_operate_routekey_edit"),
    POSITION_OPERATE_ROUTEKEY_RESYNC("position_operate_routekey_resync"),
    POSITION_OPERATE_ROUTEKEY_DOWNSHELF("position_operate_routekey_downshelf"),
    POSITION_OPERATE_ROUTEKEY_DEL("position_operate_routekey_del"),
    HR_OPERATOR_ALL_HISTORY("hr_operator_all_record_queue"),
    HR_OPERATOR_ALL_HISTORY_ROUTEKEY("hr_operator_all_record_routekey"),
    HR_OPERATOR_ALL_HISTORY_EXCHANGE("hr_operator_all_record_exchange"),
    HR_INTERVIEW_NOTICE_EXCHANGE("hr_interview_notice_exchange"),
    HR_INTERVIEW_NOTICE_QUEUE("hr_interview_notice_queue"),
    HR_INTERVIEW_NOTICE_ROUTEKEY("hr_interview_notice_routekey"),
    HR_INTERVIEW_CANCEL_NOTICE_EXCHANGE("hr_interview_cancel_notice_exchange"),
    HR_INTERVIEW_CANCEL_NOTICE_QUEUE("hr_interview_cancel_notice_queue"),
    HR_INTERVIEW_CANCEL_NOTICE_ROUTEKEY("hr_interview_cancel_notice_routekey"),
    POSITION_QUEUE_UPDATE_PUBLISHER("position_queue_update_publisher"),
    POSITION_QUEUE_UPDATE_PUBLISHER_ROUTEKEY("position_queue_update_publisher_routekey"),
    POSITION_QUEUE_UPDATE_PUBLISHER_EXCHANGE("position_queue_update_publisher_exchange"),
    NEW_PROCESS_ATS_MESSSAGE_QUEUE("new_process_ats_message_queue"),
    NEW_PROCESS_ATS_MESSSAGE_EXCHANGE("new_process_ats_message_exchage"),
    NEW_PROCESS_ATS_MESSSAGE_ROUTINGKEY("new_process_ats_message_routingkey"),
    NEW_MESSSAGE_NOTICE_QUEUE("new_message_notice_queue"),
    NEW_MESSSAGE_NOTICE_EXCHANGE("new_message_notice_exchage"),
    NEW_MESSSAGE_NOTICE_ROUTINGKEY("new_message_notice_routingkey"),
    MESSAGE_CENTER_NOTICE_EXCHANGE("message_center_notice_exchange"),
    MESSAGE_CENTER_NOTICE_QUEUE("message_center_notice_queue"),
    MESSAGE_CENTER_NOTICE_ROUTEKEY("message_center_notice_routekey"),
    INTERVIEWER_IMPORT_EXCHANGE("interviewer_import_exchange"),
    INTERVIEWER_IMPORT_QUEUE("interviewer_import_queue"),
    INTERVIEWER_IMPORT_ROUTEKEY("interviewer_import_routekey"),
    INTERVIEW_IMMEDIATE_QUEUE("interview_refresh_status_immediate_queue"),
    INTERVIEW_DELAY_EXCHANGE("interview_refresh_status_delay_exchange"),
    INTERVIEW_DELAY_ROUTEKEY("interview_refresh_status_delay_routekey"),
    OPERATION_LOG_EXCHANGE("operation_log_exchange"),
    OPERATION_LOG_QUEUE("operation_log_queue"),
    OPERATION_LOG_ROUTEKEY("operation_log_routekey"),
    POSITION_PROCESS_EXCHANGE("position_process_exchange"),
    POSITION_PROCESS_QUEUE("position_process_queue"),
    POSITION_PROCESS_ROUTEKEY("position_process_routekey")
    ;

    private String value;
    private  RabbitMQConstant(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
