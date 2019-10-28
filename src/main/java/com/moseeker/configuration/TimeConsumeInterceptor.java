package com.moseeker.configuration;

import com.moseeker.constant.LogType;
import com.moseeker.util.LogUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.moseeker.constant.TraceConstant.*;

@Component
public class TimeConsumeInterceptor implements HandlerInterceptor {

    private long start = 0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Map<String, Object> map = new java.util.concurrent.ConcurrentHashMap<>();
        try {
            MDC.put(URL,request.getRequestURI());
            MDC.put(METHOD,request.getMethod());
            String timeConsume =(System.currentTimeMillis() - start)+"";
            MDC.put(TIMECONSUME,timeConsume);
            LogUtil.CommonLog(LogType.Info, String.format("耗时监控：%s",timeConsume));
        } catch (Exception e) {
            map.put("error", e.toString());
            LogUtil.CommonLog(LogType.Error, "耗时监控异常", "", "", map);
        }finally {
            MDC.remove(URL);
            MDC.remove(METHOD);
            MDC.remove(TIMECONSUME);
        }
    }
}

