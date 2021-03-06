package com.moseeker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public TraceInterceptor getTraceInterceptor() {
        return new TraceInterceptor();
    }

    @Bean
    public TimeConsumeInterceptor getTimeConsumeInterceptor() {
        return new TimeConsumeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTraceInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getTimeConsumeInterceptor()).addPathPatterns("/**");
    }
}
