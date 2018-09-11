package com.moseeker.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "redis")
@Component
@Data
public class ConfigRedis {
	
	private List<String> list;

}
