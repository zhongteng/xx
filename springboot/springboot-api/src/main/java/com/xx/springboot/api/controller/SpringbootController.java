package com.xx.springboot.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.xx.springboot.model.UserRegisterInfo;
import com.xx.springboot.service.SpringbootService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@Api(value = "SpringBoot测试接口", tags = { "用户操作接口" })
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

	@RequestMapping(value = "getIdentityInfoByPhone", method = RequestMethod.GET, produces = "application/json")
	public String getIdentityInfoByPhone(String phone) throws JsonProcessingException {
		return springbootService.getIdentityInfoByPhone(phone);
	}

	@ApiOperation(authorizations = { @Authorization(value = "sss", scopes = {
			@AuthorizationScope(scope = "query:identity:info", description = "允许查询个人信息") }) }, value = "查询个人信息")
	@RequestMapping(value = "queryIdentityInfo", method = RequestMethod.POST, produces = "application/json")
	public String queryIdentityInfo(
			@RequestBody @ApiParam(value = "用户查询参数", required = true) UserRegisterInfo userRegisterInfo)
			throws JsonProcessingException {
		String phone = userRegisterInfo.getPhone();
		return springbootService.getIdentityInfoByPhone(phone);
	}
}
