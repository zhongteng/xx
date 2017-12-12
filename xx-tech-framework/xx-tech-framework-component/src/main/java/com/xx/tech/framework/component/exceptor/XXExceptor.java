package com.xx.tech.framework.component.exceptor;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class XXExceptor extends PropertyPlaceholderConfigurer implements InitializingBean {
	
	protected static Logger logger = LoggerFactory.getLogger(XXExceptor.class);
	
	protected static ConcurrentHashMap<String, String> exceptionMap = new ConcurrentHashMap<>();
	
	private static final String TECH_FRAMEWORK_CONFIG_ROOT_PATH = "/exceptor/tech-framework-exception.properties";
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		System.out.println(props);
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.setFileEncoding("UTF-8");
		super.setLocations(new PathMatchingResourcePatternResolver().getResources(TECH_FRAMEWORK_CONFIG_ROOT_PATH));
	}
	
}
