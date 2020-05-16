package com.snkit.springbootmayswaggerdemoclient;

import feign.RequestInterceptor;
import feign.RequestTemplate;


public class CustomFeignInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		template.header("token", "12345");
		//template.header("token", "3456");
		System.out.println("  From Custom Feign Interceptor");
	}

}
