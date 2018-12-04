package com.moseeker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfiguration {

	@Value("${security.basic.enabled}")
	private boolean enabled;
	@Value("${security.user.name}")
	private String name;
	@Value("${security.user.password}")
	private String password;
	
	@Bean
	@ConditionalOnProperty(value = {"security.basic.enabled"}, matchIfMissing = false)
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(name, password);
	}

}
