package com.xx.tech.framework.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.tech.framework.component.configurer.XXConfigurer;
import com.xx.tech.framework.component.manager.XXManager;
import com.xx.tech.framework.core.exception.BusiException;
import com.xx.tech.framework.springboot.exception.XXExceptionHandler;
import com.xx.tech.framework.springboot.service.XXService;
import com.xx.tech.framework.springboot.utils.SpringContextUtil;

@RestController
@RequestMapping(value = "controller")
public class XXController extends XXExceptionHandler {

	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private XXService service;
	// @Autowired
	// private DefaultParser defaultParser;
//	@Value("${xx.tech.framework.exceptor.config.path}")
	private String exceptorPath;
//	@Autowired
//	private XXConfigurer xxConfigurer;
	@Autowired
	private XXManager xxManager;

	@RequestMapping(value = "hello", method = RequestMethod.GET, produces = "json/application")
	public String hello(@RequestParam String name, @RequestParam String data) throws JsonProcessingException {
		System.out.println("name:" + name);
		System.out.println("data:" + data);
		// defaultParser.parse();
		String result = service.hello(name);
		Map<String, Object> map = new HashMap<>();
		map.put("result", new StringBuffer(name).append("-").append(result).toString());
		ApplicationContext context = SpringContextUtil.getApplicationContext();
		System.out.println(mapper.writeValueAsString(context.getEnvironment()));
		return mapper.writeValueAsString(map);
	}

	@RequestMapping(value = "getPath", method = RequestMethod.GET, produces = "json/application")
	public String getPath() throws JsonProcessingException {
		String key = "xx.tech.framework.exceptor.path";
		Map<String, Object> map = new HashMap<>();
		map.put("result", new StringBuffer(key).append("-").append(exceptorPath).toString());
		return mapper.writeValueAsString(map);
	}

//	@RequestMapping(value = "getAllProperties", method = RequestMethod.GET, produces = "json/application")
//	public String getAllProperties() throws JsonProcessingException {
//		return mapper.writeValueAsString(xxConfigurer.getAllProperties());
//	}
	
	@RequestMapping(value = "getConfigByComponentId", method = RequestMethod.GET, produces = "json/application")
	public String getConfigByComponentId() throws JsonProcessingException {
		return xxManager.getConfigByComponentId();
	}

	@RequestMapping(value = "getContextDetail", method = RequestMethod.GET, produces = "json/application")
	public String getContextDetail() throws JsonProcessingException {
		ApplicationContext context = SpringContextUtil.getApplicationContext();
		Map<String, Object> map = new HashMap<>();
		map.put("result", context.getBeanDefinitionNames());
		return mapper.writeValueAsString(map);
	}

	@RequestMapping(value = "exception", method = RequestMethod.GET, produces = "json/application")
	public String exception() throws Exception {
		if (true) {
			throw new Exception("sss");
		}
		return null;
	}
	
	@RequestMapping(value = "busiException", method = RequestMethod.POST, produces = "json/application")
	public String busiException() throws Exception {
		if (true) {
			throw new BusiException("sss");
		}
		return null;
	}

}
