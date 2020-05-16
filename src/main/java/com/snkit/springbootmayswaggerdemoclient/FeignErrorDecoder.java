package com.snkit.springbootmayswaggerdemoclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	public static Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);
	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		System.out.println(response.status()+"Error took place when using Feign client to send HTTP Request. Status code "
				+methodKey );
		switch (response.status()) {
		case 400:
			logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
		case 404: {
			logger.error("Error took place when using Feign client to send HTTP Request. Status code "
					+ response.status() + ", methodKey = " + methodKey);
			return new DemoResponseStatusException(response.status(),
					"Error Occured while processing request");
		}
	
		default:
			return new DemoResponseStatusException(response.status(),
					"Error Occured while processing request");
		}
	}

}
