package org.config.eureka.amqp.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
public class ConfigEurekaAMQPClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaAMQPClientApplication.class, args);
    }

    @Value("${foo}")
    String foo;
    @RequestMapping(value = "/config")
    public String config(){
        return foo;
    }
}
