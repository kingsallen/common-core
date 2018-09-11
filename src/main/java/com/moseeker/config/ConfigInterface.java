package com.moseeker.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "interfaceid")
@Component
@Data
public class ConfigInterface {
	
	private List<String> list;

}
