package com.moseeker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "info")
@Component
@Data
public class ConfigInfo {

    private String name;
    private String port;
    private String description;
    private String version;
    private String developer;
    private Map<String, String> mapped;

}
