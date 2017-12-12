package com.xx.tech.framework.component.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.tech.framework.component.AXXComponent;
import com.xx.tech.framework.component.configurer.XXConfigurer;
import com.xx.tech.framework.component.exceptor.XXExceptor;
import com.xx.tech.framework.component.parser.XXParser;

@Component
public class XXManager extends AXXComponent implements InitializingBean {
	
	protected static Logger logger = LoggerFactory.getLogger(XXManager.class);
	
	private static final String COMP_MANAGER_ID = "T001";
	
	private static final String PROP_MANAGER_PATH = "/tech-framework.properties";
	
//	@Bean
//	public XXParser xxParser() {
//		logger.info("XXParser");
//		return new XXParser();
//	}
//	
//	@Bean
//	public XXExceptor xxExceptor() {
//		logger.info("XXExceptor");
//		return new XXExceptor();
//	}
//	
//	@Bean
//	public XXConfigurer xxConfigurer() {
//		logger.info("XXConfigurer");
//		return new XXConfigurer();
//	}

	@Override
	public void parseProperties(String path) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(path);
		
		super.setPath(path);
		System.out.println(getConfigByComponentId(COMP_MANAGER_ID));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.init(COMP_MANAGER_ID, PROP_MANAGER_PATH);
	}
	
	public String getConfigByComponentId() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getConfigByComponentId(COMP_MANAGER_ID));
	}
}
