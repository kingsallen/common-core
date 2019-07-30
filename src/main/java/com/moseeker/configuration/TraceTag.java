package com.moseeker.configuration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
public class TraceTag {

    @Autowired
    BeanFactory beanFactory;
    private Tracer tracer = null;


    private Tracer tracer() {
        if (this.tracer == null) {
            if (beanFactory != null) {
                this.tracer = this.beanFactory.getBean(Tracer.class);
            }
        }
        return this.tracer;
    }

    public void addTag(String key, String value) {
        Tracer tracer = tracer();
        if (tracer != null) {
            Span span = tracer.getCurrentSpan();
            if (span != null) {
                span.tag(key, value);
            }
        }
    }
}
