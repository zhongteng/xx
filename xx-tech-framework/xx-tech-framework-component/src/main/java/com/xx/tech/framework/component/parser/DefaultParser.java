package com.xx.tech.framework.component.parser;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import com.xx.tech.framework.component.configurer.XXConfigurer;

public abstract class DefaultParser implements IParser {
	
	@Autowired
	private XXConfigurer configurer;

	public DefaultParser() {

	}

	public DefaultParser(Resource location) {
		
	}

	@Override
	public void parse() throws IOException {
		// TODO Auto-generated method stub

	}
	
}
