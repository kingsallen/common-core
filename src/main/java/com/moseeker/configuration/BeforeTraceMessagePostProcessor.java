package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import com.moseeker.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class BeforeTraceMessagePostProcessor implements MessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        String traceId = MDC.get(TraceConstant.CUSTOM_TRACE_ID);
        if (StringUtils.isNullOrEmpty(traceId)) {
            traceId = findTraceFromRequest();
        }
        if (StringUtils.isNullOrEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        message.getMessageProperties().setHeader(TraceConstant.HEADERNAME, traceId);
        String sleuthTraceId = MDC.get(TraceConstant.X_B3_TRACE_ID);
        if(!StringUtils.isNullOrEmpty(sleuthTraceId)){
            message.getMessageProperties().setHeader(TraceConstant.SLEUTHHEADERNAME, sleuthTraceId);
        }
        return message;
    }

    private String findTraceFromRequest() {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return "";
        }
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
        if (servletRequestAttributes == null) {
            return "";
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request == null) {
            return "";
        }
        String traceId = request.getHeader(TraceConstant.HEADERNAME);
        return traceId;
    }
}
