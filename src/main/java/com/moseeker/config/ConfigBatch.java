package com.moseeker.config;

import com.moseeker.util.BatchTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBatch {
    @Bean
    public BatchTask getBatchTask(){
        return new BatchTask();
    }
}
