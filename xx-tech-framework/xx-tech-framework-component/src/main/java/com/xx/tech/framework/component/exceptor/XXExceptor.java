package com.xx.tech.framework.component.exceptor;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xx.tech.framework.component.parser.DefaultParser;

@Component
public class XXExceptor extends DefaultParser implements InitializingBean {
	
	protected static ConcurrentHashMap<String, String> exceptionMap = new ConcurrentHashMap<>();
	
	@Value("${xx.tech.framework.exceptor.config.path}")
	public String exceptorPath;

	public void parse() {
//		super.parse();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.parse();
		
	}
	
}
