package com.xx.tech.framework.component.parser;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class XXParser extends PropertyPlaceholderConfigurer implements InitializingBean {
	
	protected Logger logger = LoggerFactory.getLogger(XXParser.class);
	
	private final String XX_APPLICATION_CONFIG_ROOT_PATH = "classpath:/application/**/*.properties";
	
	public void parse(String path) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			super.setLocations(resolver.getResources(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("XX技术框架组件管理者启动中...");
		this.parse(XX_APPLICATION_CONFIG_ROOT_PATH);
	}

}
