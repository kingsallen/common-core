package com.moseeker.configuration;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class RibbonConfiguration {
	
	@Value("${security.basic.enabled}")
	private boolean enabled;
	@Value("${security.user.name}")
	private String name;
	@Value("${security.user.password}")
	private String password;

	@Bean
	@ConditionalOnProperty(value = {"security.basic.enabled"}, matchIfMissing = false)
	public HttpHeaders httpHeaders() {
		HttpHeaders headers = new HttpHeaders(); // 定义一个HTTP的头信息
		String auth = name + ":" + password; // 认证的原始信息
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
		// 在进行授权的头信息内容配置的时候加密的信息一定要与“Basic”之间有一个空格
		String authHeader = "Basic" + " " + new String(encodedAuth);
		headers.set("Authorization", authHeader);
		return headers;
	}

}
