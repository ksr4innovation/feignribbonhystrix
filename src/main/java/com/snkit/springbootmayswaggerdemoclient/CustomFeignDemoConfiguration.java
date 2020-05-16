package com.snkit.springbootmayswaggerdemoclient;

import org.springframework.context.annotation.Bean;

public class CustomFeignDemoConfiguration {
	
	@Bean
	public CustomFeignInterceptor customFeignInterceptor() {
		return new CustomFeignInterceptor();
	}

	/*@Bean
	public FeignErrorDecoder errorDecoder() {
		return new FeignErrorDecoder();
	}*/
	
	
}
