package com.moseeker.configuration;

import com.moseeker.constant.LogType;
import com.moseeker.util.LogUtil;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private  long start =0;

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
        StringBuffer sb = new StringBuffer();
        sb.append("request method:" + request.getMethod()+"\t");
        sb.append("request url:" + request.getRequestURL().toString()+"\t");
        sb.append("耗时："+(System.currentTimeMillis()-start)+"毫秒");
        LogUtil.CommonLog(LogType.Info, sb.toString());
    }
}
