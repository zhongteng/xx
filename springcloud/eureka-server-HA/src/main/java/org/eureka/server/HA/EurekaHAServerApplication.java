package org.eureka.server.HA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaHAServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaHAServerApplication.class, args);
	}
}
