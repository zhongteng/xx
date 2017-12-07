package com.xx.tech.framework.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.tech.framework.springboot.handler.XXComponent;

@Service
public class XXService {
	
	@Autowired
	private XXComponent component;

	public String hello(String name) {
		if (name != null && name.equals("benjamin")) {
			return component.hello(true);
		} else {
			return component.hello(false);
		}
	}
}
