package com.xx.tech.framework.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public abstract class AXXComponent extends PropertyPlaceholderConfigurer implements IXXComponent {
	
	protected static Logger logger = LoggerFactory.getLogger(AXXComponent.class);
	
	private static Map<String, ConcurrentHashMap<String, String>> componentConfigMap = new HashMap<>();
	
	protected static ConcurrentHashMap<String, String> configMap = new ConcurrentHashMap<>();
	
	private String componentId;
	
	private ConfigurableListableBeanFactory beanFactory;
	
	@Override
	public void init(String componentId, String path) throws Exception {
		// TODO Auto-generated method stub
		this.componentId = componentId;
		this.parseProperties(path);
	}
	
	public abstract void parseProperties(String path) throws Exception;

	protected ConcurrentHashMap<String, String> getConfigByComponentId(String componentId) {
		return componentConfigMap.get(componentId);
	}
	
	protected void setPath(String path) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		super.setLocation(resolver.getResource(path));
	}
	
	protected void processProperties(Properties props) {
		super.processProperties(beanFactory, props);
	}
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		this.beanFactory = beanFactory;
		super.processProperties(beanFactory, props);
		StringBuffer sb = new StringBuffer();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			configMap.put(keyStr, value);
			sb.append("[" +keyStr + ":" + value + "]\r\n");
		}
		componentConfigMap.put(componentId, configMap);
		logger.info("框架基础配置信息：\r\n{}", sb.toString());
	}
}
