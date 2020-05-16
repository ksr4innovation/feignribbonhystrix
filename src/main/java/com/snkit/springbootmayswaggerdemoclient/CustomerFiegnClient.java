package com.snkit.springbootmayswaggerdemoclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.snkit.springbootmayswaggerdemoclient.models.Employe;
import com.snkit.springbootmayswaggerdemoclient.models.EmployeResponse;

import feign.hystrix.FallbackFactory;
@RibbonClient(name="CUSTOMER")
/*@FeignClient(name="CUSTOMER",configuration=CustomFeignDemoConfiguration.class,
fallback=CustomerFiegnClientFallBack.class)*/
@FeignClient(name="CUSTOMER",configuration=CustomFeignDemoConfiguration.class,
fallbackFactory=CustomerFiegnClientFallBackFactory.class)
public interface CustomerFiegnClient {
	
	@PostMapping(value = "/saveEmploye", consumes = { "application/json" },
			produces = { "application/json" })
	EmployeResponse saveEmploye(@RequestBody Employe emp);
	
	@GetMapping(value = "/customer/getCust", consumes = { "application/json" },
			produces = { "application/json" })
	public EmployeResponse getCust();
	
	 @RequestMapping(value = "/customer/findCustById/{custId}",
		        produces = { "application/json", "application/xml" }, 
		        method = RequestMethod.GET)
	 public    Employe findCustById(@PathVariable("custId") String custId);


}

/*@Component
 class CustomerFiegnClientFallBack implements CustomerFiegnClient{

	@Override
	public EmployeResponse saveEmploye(Employe emp) {
		// TODO Auto-generated method stub
		return new EmployeResponse();
	}

	@Override
	public EmployeResponse getCust() {
		// TODO Auto-generated method stub
		return new EmployeResponse();
	}

	@Override
	public Employe findCustById(String custId) {
		System.out.println("  From Custom Feign CustomerFiegnClientFallBack");
		return new Employe();
	}
	
}*/

@Component
class CustomerFiegnClientFallBackFactory  implements FallbackFactory<CustomerFiegnClient>{

	@Override
	public CustomerFiegnClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new CustomerFiegnClient() {

			@Override
			public EmployeResponse saveEmploye(Employe emp) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EmployeResponse getCust() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Employe findCustById(String custId) {
				System.out.println("  From Custom Feign CustomerFiegnClientFallBack  "+cause.getMessage());
				System.out.println("  From Custom Feign CustomerFiegnClientFallBack");
				return new Employe();
			}
			
		};
	}
	
}
