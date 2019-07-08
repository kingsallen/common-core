package com.moseeker.configuration;

import com.moseeker.constant.TraceConstant;
import com.moseeker.util.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Configuration
public class FeignTraceInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) requestAttributes);
        if (servletRequestAttributes == null) {
            return;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request == null) {
            return;
        }
        String traceId = request.getHeader(TraceConstant.HEADERNAME);
        if (StringUtils.isNullOrEmpty(traceId)) {
            return;
        }
        template.header(TraceConstant.HEADERNAME, traceId);
    }
}
