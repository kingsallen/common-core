package com.moseeker.configuration;

import com.moseeker.constant.LogType;
import com.moseeker.constant.TraceConstant;
import com.moseeker.util.LogUtil;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TraceInterceptor implements HandlerInterceptor {

    protected String uuid = "";

    @Autowired
    TraceTag traceTag;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        try {
            uuid = httpServletRequest.getHeader(TraceConstant.HEADERNAME);
            if (StringUtils.isNullOrEmpty(uuid)) {
                uuid = UUID.randomUUID().toString();
            }
            MDC.put(TraceConstant.CUSTOM_TRACE_ID, uuid);
            if (traceTag != null) {
                traceTag.addTag(TraceConstant.CUSTOM_TAG, uuid);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("exception", e.toString());
            LogUtil.CommonLog(LogType.Error, "TraceInterceptor异常", "", "", map);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        try {
            MDC.remove(TraceConstant.CUSTOM_TRACE_ID);
        } catch (Exception ex) {
            Map<String, Object> map = new HashMap<>();
            map.put("exception", ex.toString());
            LogUtil.CommonLog(LogType.Error, "TraceInterceptor异常", "", "", map);
        }
    }
}
