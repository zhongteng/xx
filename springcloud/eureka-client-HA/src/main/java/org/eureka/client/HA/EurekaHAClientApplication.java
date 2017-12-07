package org.eureka.client.HA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaHAClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaHAClientApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/helloworld")
	public String helloWorld(@RequestParam String name) {
		return "Hello World To " + name + " From Port:" + port;
	}
}
