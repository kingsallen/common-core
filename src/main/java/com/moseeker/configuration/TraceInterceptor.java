package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import com.moseeker.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class TraceInterceptor implements HandlerInterceptor {

    protected String uuid = "";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        uuid = httpServletRequest.getHeader(TraceConstant.HEADERNAME);
        if (StringUtils.isNullOrEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        MDC.put(TraceConstant.LOGKEY, uuid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        MDC.remove(TraceConstant.LOGKEY);
    }
}
