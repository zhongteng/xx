package com.xx.springboot.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.xx.springboot")
@MapperScan("com.xx.springboot.dao")
@PropertySource(value = {"configs/config.properties"}, encoding = "UTF-8")
//@ImportResource(value = {"dubbo/dubbo-provider.xml", "dubbo/dubbo-consumer.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}