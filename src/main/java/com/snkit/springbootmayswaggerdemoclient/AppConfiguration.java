package com.snkit.springbootmayswaggerdemoclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
/*	@Bean
	public RestTemplate customrestTemplate() {
		return new RestTemplate();
	}*/
	/*@Bean
	public IRule loadBlancingRule (){
		 return new RoundRobinRule();
	}
	@Bean
	public IPing pingConfiguration(ServerList<Server> servers) {
	    
	  String pingPath = "/actuator/health";
	  IPing ping = new PingUrl(false, pingPath);        
	  System.out.println("Configuring ping URI to [{}]"+ pingPath);
	    
	  return ping;    
	}*/
	@Bean
	public HelloWorldService helloWorldService() {
	
		System.out.println("  Helloworld service ===================");
		
		return new HelloWorldService();
	}
}
