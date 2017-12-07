package com.xx.tech.framework.component.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class XXParser implements InitializingBean, Ordered {
	
	protected static Logger logger = LoggerFactory.getLogger(XXParser.class);
	
	private static final String TECH_FRAMEWORK_CONFIG_ROOT_PATH = "/tech-framework.properties";

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
