package com.moseeker.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @Description TODO
 * @Author Rays
 * @DATE 2019-09-03
 **/
@Configuration
public class ConfigFileUpload {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("5242880KB");
        /// 总上传数据大小
        factory.setMaxRequestSize("5242880KB");
        return factory.createMultipartConfig();
    }


}
