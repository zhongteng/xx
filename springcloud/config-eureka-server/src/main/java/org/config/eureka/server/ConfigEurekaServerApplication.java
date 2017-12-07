package org.config.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigServer
public class ConfigEurekaServerApplication {
	public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaServerApplication.class, args);
    }
}
