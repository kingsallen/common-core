package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import com.moseeker.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class TraceInterceptor implements HandlerInterceptor {

    protected String uuid = "";

    @Autowired
    BeanFactory factory;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        uuid = httpServletRequest.getHeader(TraceConstant.HEADERNAME);
        if (StringUtils.isNullOrEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        MDC.put(TraceConstant.CUSTOM_TRACE_ID, uuid);
        if (factory != null) {
            Tracer tracer = factory.getBean(Tracer.class);
            if (tracer != null) {
                Span span = tracer.getCurrentSpan();
                if (span != null) {
                    span.tag(TraceConstant.CUSTOM_Tag, uuid);
                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        MDC.remove(TraceConstant.CUSTOM_TRACE_ID);
    }
}
