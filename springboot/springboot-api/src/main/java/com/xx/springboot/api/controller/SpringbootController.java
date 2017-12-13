package com.xx.springboot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.xx.springboot.model.UserRegisterInfo;
import com.xx.springboot.service.SpringbootService;

@RestController
public class SpringbootController {

	@Value(value = "${default.name}")
	private String defaultName;
	
	@Autowired
	private SpringbootService springbootService;

	@RequestMapping(value = "hello", method = RequestMethod.GET, produces = "application/json")
	public String hello(String phone) throws JsonProcessingException {
		return springbootService.hello(phone);
	}
	
	@RequestMapping(value = "getMaxId", method = RequestMethod.GET)
	public Long getMaxId() throws JsonProcessingException {
		return springbootService.getMaxId();
	}
	
	@RequestMapping(value = "getByPhone", method = RequestMethod.GET, produces = "application/json")
	public String getByPhone(String phone) throws JsonProcessingException {
		UserRegisterInfo user = springbootService.getByPhone(phone);
		user.setName(defaultName);
		return user.toJSON();
	}
	
	@RequestMapping(value = "getByPage", method = RequestMethod.GET, produces = "application/json")
	public String getByPage(String phone) throws JsonProcessingException {
		List<UserRegisterInfo> user = springbootService.getByPage(0, 15);
		return new ObjectMapper().writeValueAsString(user);
	}

}
