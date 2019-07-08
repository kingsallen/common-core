package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ListenerAspect {

    @Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object proceed(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] args = thisJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Message) {
                Message message = (Message) args[i];
                Object traceId = message.getMessageProperties().getHeaders().get(TraceConstant.HEADERNAME);
                if (traceId != null) {
                    MDC.put(TraceConstant.CUSTOM_TRACE_ID, traceId.toString());
                }
                Object sleuthTraceId = message.getMessageProperties().getHeaders().get(TraceConstant.SLEUTHHEADERNAME);
                if (traceId != null) {
                    MDC.put(TraceConstant.X_B3_TRACE_ID, sleuthTraceId.toString());
                }
                break;
            }
        }
        Object object = thisJoinPoint.proceed(args);
        MDC.remove(TraceConstant.CUSTOM_TRACE_ID);
        MDC.remove(TraceConstant.X_B3_TRACE_ID);
        return object;
    }
}
