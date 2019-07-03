package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ListenerAspect {
    @Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener) && args(message)")
    public void pointCut(Message message) {
    }

    @Before(value = "pointCut(message)")
    public void beforeConsume(Message message) {
        Object obj = message.getMessageProperties().getHeaders().get(TraceConstant.HEADERNAME);
        if(obj!=null){
            MDC.put(TraceConstant.LOGKEY, obj.toString());
        }
    }

    @After(value = "pointCut(message)")
    public  void afterConSume(Message message){
        MDC.remove(TraceConstant.LOGKEY);
    }
}
