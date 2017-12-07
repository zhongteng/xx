package com.xx.tech.framework.component.resource;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class XXResourceLoader implements ResourceLoader {
	
	private static final String TECH_FRAMEWORK_CONFIG_ROOT_PATH = "/tech-framework.properties";

	@Override
	public Resource getResource(String location) {
		// TODO Auto-generated method stub
		System.out.println("getResource");
		return new PathResource(location);
	}

	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		System.out.println("getClassLoader");
		return null;
	}

}
