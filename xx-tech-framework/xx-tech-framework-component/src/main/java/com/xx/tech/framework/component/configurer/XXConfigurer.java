package com.xx.tech.framework.component.configurer;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

public class XXConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean {
	
	protected static Logger logger = LoggerFactory.getLogger(XXConfigurer.class);
	
	protected ConcurrentHashMap<String, String> rootConfigMap = new ConcurrentHashMap<>();

	private static final String PRODUCTION_MODE = "production.mode";
	// 缓存所有的属性配置
	private Properties properties;

	public XXConfigurer() {

	}

	public XXConfigurer(Resource location) {
		super.setLocation(location);
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return properties.getProperty(PRODUCTION_MODE);
	}
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		System.out.println("process");
		super.processProperties(beanFactory, props);

		StringBuffer sb = new StringBuffer();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			rootConfigMap.put(keyStr, value);
			sb.append("[" +keyStr + ":" + value + "]\r\n");
		}
		logger.info("框架基础配置信息：\r\n{}", sb.toString());
	}
	
	@Override
	protected Properties mergeProperties() throws IOException {
		System.out.println("merge");
		Properties mergeProperties = super.mergeProperties();
		// 根据路由原则，提取最终生效的properties
		this.properties = new Properties();
		// 获取路由规则,系统属性设置mode优先
		String mode = System.getProperty(PRODUCTION_MODE);
		if (StringUtils.isEmpty(mode)) {
			String str = mergeProperties.getProperty(PRODUCTION_MODE);
			mode = str != null ? str : "LOCAL";
		}
		properties.put(PRODUCTION_MODE, mode);
		String[] modes = mode.split(",");
		Set<Entry<Object, Object>> es = mergeProperties.entrySet();
		for (Entry<Object, Object> entry : es) {
			String key = (String) entry.getKey();
			int idx = key.lastIndexOf('_');
			String realKey = idx == -1 ? key : key.substring(0, idx);
			if (!properties.containsKey(realKey)) {
				Object value = null;
				for (String md : modes) {
					value = mergeProperties.get(realKey + "_" + md);
					if (value != null) {
						properties.put(realKey, value);
						break;
					}
				}
				if (value == null) {
					value = mergeProperties.get(realKey);
					if (value != null) {
						properties.put(realKey, value);
					} else {
						throw new RuntimeException("impossible empty property for " + realKey);
					}
				}
			}
		}
		return properties;
	}

	/**
	 * 开放此方法给需要的业务
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return resolvePlaceholder(key, properties);
	}

	/**
	 * 开放此方法给需要的业务
	 * 
	 * @param key
	 * @return
	 */
	public String getAllProperties() {
		return rootConfigMap.toString();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		logger.info("XX技术框架-配置管理者启动。。。");
	}
}
