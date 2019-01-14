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
    POSITION_OPERATE_ROUTEKEY_DEL("position_operate_routekey_del")
    ;

    private String value;
    private  RabbitMQConstant(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
