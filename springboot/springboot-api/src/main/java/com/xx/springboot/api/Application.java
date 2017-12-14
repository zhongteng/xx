package com.xx.springboot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xx.springboot"})
@PropertySource(value = "configs/config.properties", encoding = "UTF-8")
@ImportResource(value = {"dubbo/dubbo-provider.xml", "dubbo/dubbo-consumer.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
