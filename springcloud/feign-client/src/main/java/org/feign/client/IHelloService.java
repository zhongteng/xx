package org.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hello-world")
public interface IHelloService {

	@RequestMapping(value = "/helloworld",method = RequestMethod.GET)
    String helloworld(@RequestParam(value = "name") String name);
}
