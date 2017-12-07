package org.feign.hystrix.client;

import org.springframework.stereotype.Component;

@Component
public class HelloHystrixService implements IHelloService {

	@Override
	public String helloworld(String name) {
		 return "sorry "+name;
	}

}
