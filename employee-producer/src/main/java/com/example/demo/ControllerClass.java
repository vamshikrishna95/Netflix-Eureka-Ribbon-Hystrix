package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ControllerClass {

	@RequestMapping("/test")
	/*for hystrix(features - fallback, circiut breaker)
	1. Add hystrix dependency in producer pom file
	2. Add @EnableCircuitBreaker in main class
	3. Add @HystrixCommand(fallbackMethod = "test1") 
	4. within double quotes enter the method to which it want to transfer.
	5. when the method continously calls the fallbackmethod then the hystrix circuit will break at some time and 
		consumer instead of calling test method it will call test1 method
	*/
	@HystrixCommand(fallbackMethod = "test1")
	public EmployeeDTO test() {
		System.err.println("Inside test");
		EmployeeDTO dto = new EmployeeDTO("123", "Vamshi", "23", "123456789");
		if (dto.getName().equalsIgnoreCase("Vamshi"))
			throw new RuntimeException();
		return dto;
	}

	public EmployeeDTO test1() {
		System.err.println("Inside Fallback");
		EmployeeDTO dto = new EmployeeDTO("111", "Krishna", "25", "789456123");
		return dto;
	}
}
