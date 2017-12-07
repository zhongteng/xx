package com.xx.tech.framework.springboot.handler;

import org.springframework.stereotype.Component;

@Component
public class XXComponent {

	public String hello(boolean xHello) {
		if (xHello) {
			return "welcome to springboot world!";
		} else {
			return "not matched user!";
		}
	}
}
