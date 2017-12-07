package org.feign.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    IHelloService helloService;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return helloService.helloworld(name);
    }
}
