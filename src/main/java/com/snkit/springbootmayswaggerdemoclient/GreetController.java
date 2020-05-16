package com.snkit.springbootmayswaggerdemoclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.snkit.springbootmayswaggerdemoclient.models.Employe;
import com.snkit.springbootmayswaggerdemoclient.models.EmployeResponse;

@RestController
public class GreetController {
	
	@Autowired
	GreetService greetService;
	
	@Autowired
	HelloWorldService helloWorldService;
	/*@Autowired
	RestTemplate customrestTemplate;*/
	
	@Autowired
	CustomerFiegnClient customerFiegnClient;
	
	@Autowired
	DiscoveryClient discoveryClient;

	@GetMapping("/greet")
	public String greet() {
		return greetService.greet();
	}
	
	@GetMapping("/sayHi")
	public String sayHi() {
		
		
	return 	helloWorldService.sayHI();
		
	}
	
	@GetMapping("/getCust")
	public EmployeResponse getCust() {
		/*ResponseEntity<EmployeResponse> response
		  = customrestTemplate.getForEntity("http://localhost:8080/customer/getCust" , EmployeResponse.class);
		
		EmployeResponse resp = response.getBody();
		return  resp;*/
		return null;
	}
	
	@GetMapping("/getCustFeign")
	public EmployeResponse getCustFeign() {
		EmployeResponse resp = customerFiegnClient.getCust();
		return  resp;
	}
	
	
	@GetMapping("/findCustById/{custId}")
	public Employe findCustById(@PathVariable("custId") String custId) {
		Employe resp = customerFiegnClient.findCustById(custId);
		return  resp;
	}
	
	
	@GetMapping("/getInstance")
	public List<String> getInstance(){
		
		List<String>  instanceList = new ArrayList<>();
		List<ServiceInstance> serviceIntanceList = discoveryClient.getInstances("CUSTOMER");
		
		serviceIntanceList.forEach(service -> {
			
			StringBuffer sb = new StringBuffer();
			sb.append("http://"+service.getHost());
			sb.append(":"+service.getPort()+"/");
			instanceList.add(sb.toString());
		});
		
		return instanceList;
	}
}
