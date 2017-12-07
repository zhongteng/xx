package com.xx.tech.framework.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;

//@ConfigurationProperties(value = "classpath:application/application.properties", prefix = "xx")
@SpringBootApplication(scanBasePackages = {"com.xx.tech.framework.springboot","com.xx.tech.framework.component"})
public class XXApplication {
	
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(XXApplication.class, args);
	}
}
