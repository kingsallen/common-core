package com.moseeker.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "appid")
@Component
@Data
public class ConfigApp {

	private List<String> list;
	private Map<String, String> map;

}
