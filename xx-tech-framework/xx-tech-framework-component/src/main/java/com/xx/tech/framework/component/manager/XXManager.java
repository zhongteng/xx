package com.xx.tech.framework.component.manager;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.xx.tech.framework.component.configurer.XXConfigurer;

@Component
public class XXManager implements InitializingBean, Ordered {
	
	protected static Logger logger = LoggerFactory.getLogger(XXManager.class);
	
	private static final String TECH_FRAMEWORK_CONFIG_ROOT_PATH = "/tech-framework.properties";

	public void init() {
		logger.info("XX技术框架组件管理者启动中...");
//		XXConfigurer config = this.initConfigurer();
	}
	
	@Bean
	public XXConfigurer initConfigurer() {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] applicationResources = resolver.getResources("classpath:/application/**/*.properties");
			for (Resource resource : applicationResources) {
				System.out.println(resource.getFilename());
			}
			System.out.println(applicationResources);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new XXConfigurer(new InputStreamResource(XXManager.class.getResourceAsStream(TECH_FRAMEWORK_CONFIG_ROOT_PATH)));
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.init();
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
