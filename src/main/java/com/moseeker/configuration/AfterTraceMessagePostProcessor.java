package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;


public class AfterTraceMessagePostProcessor implements MessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        Object traceId = message.getMessageProperties().getHeaders().get(TraceConstant.HEADERNAME);
        MDC.put(TraceConstant.LOGKEY, traceId.toString());
        return message;
    }
}
