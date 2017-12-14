package com.xx.springboot.dubbo;

import org.springframework.stereotype.Component;

@Component
public class SpringbootDubboService implements ISpringbootDubboService {

	public void hello() {
		System.out.println("hello");
	}

}
