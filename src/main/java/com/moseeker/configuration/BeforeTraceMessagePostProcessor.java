package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import com.moseeker.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class BeforeTraceMessagePostProcessor implements MessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        String traceId = MDC.get(TraceConstant.LOGKEY);
        if (StringUtils.isNullOrEmpty(traceId)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            traceId = request.getHeader(TraceConstant.HEADERNAME);
        }
        if (StringUtils.isNullOrEmpty(traceId)){
            traceId =  UUID.randomUUID().toString();
        }
        message.getMessageProperties().setHeader(TraceConstant.HEADERNAME, traceId);
        return message;
    }
}
