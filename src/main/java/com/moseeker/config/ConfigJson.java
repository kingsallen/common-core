package com.moseeker.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigJson extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {

        if (bean instanceof ObjectMapper) {
            ObjectMapper mapper = (ObjectMapper) bean;
            mapper.setFilterProvider(getFilters());
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Bean
    private static FilterProvider getFilters() {
        SimpleFilterProvider filters = new SimpleFilterProvider();
        filters.addFilter("emptyfilter", new PropertyFilter() {
            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {
                if (writer instanceof BeanPropertyWriter) {
                    Object value = ((BeanPropertyWriter) writer).get(pojo);
                    if (value == null) {
                        return;
                    }
                    if (value instanceof List) {
                        List list = (List) value;
                        if (list.size() == 0) {
                            return;
                        }
                    }
                    if (value instanceof String) {
                        if (value.toString().equals("")) {
                            return;
                        }
                    }
                }
                writer.serializeAsField(pojo, jgen, prov);
            }

            @Override
            public void serializeAsElement(Object elementValue, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {

            }

            @Override
            public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {

            }

            @Override
            public void depositSchemaProperty(PropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {

            }
        });
        return filters;
    }
}
