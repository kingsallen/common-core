package com.moseeker.configuration;

import com.moseeker.constant.LogType;
import com.moseeker.util.LogUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LogInterceptor implements HandlerInterceptor {

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
            map.put("requestMethod", request.getMethod());
            map.put("requestUrl", request.getRequestURL());
            map.put("耗时", (System.currentTimeMillis() - start) + "ms");
            LogUtil.CommonLog(LogType.Info, "耗时监控", "", "", map);
        } catch (Exception e) {
            map.put("error", e.toString());
            LogUtil.CommonLog(LogType.Error, "耗时监控异常", "", "", map);
        }
    }
}

