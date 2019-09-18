package com.moseeker.configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 过滤器
 *
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        XssAndSqlHttpServletRequestWrapper xssFilterRequest = new XssAndSqlHttpServletRequestWrapper(request);
        chain.doFilter(xssFilterRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}