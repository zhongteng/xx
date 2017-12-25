package com.xx.springboot.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.springboot.model.DeviceInfo;
import com.xx.springboot.service.SpringbootService;

import io.swagger.annotations.Api;

@Api(value = "SpringBoot测试接口", tags = { "数据库操作接口" })
@RestController
public class SpringbootController {

	@Value(value = "${default.name}")
	private String defaultName;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SpringbootService springbootService;
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		DeviceInfo device = mapper.readValue(json, DeviceInfo.class);
		springbootService.insertDevice(device);
	}
	
	@RequestMapping(value = "batchInsert", method = RequestMethod.POST)
	public void batchInsert(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, DeviceInfo.class);
		List<DeviceInfo> deviceList = mapper.readValue(json, type);
		springbootService.batchInsertDevices(deviceList);
	}
	
	@RequestMapping(value = "selectOne", method = RequestMethod.POST, produces = "application/json")
	public String selectOne(@RequestBody String json) throws IOException {
		DeviceInfo device = mapper.readValue(json, DeviceInfo.class);
		DeviceInfo result = springbootService.getDevice(device);
		return mapper.writeValueAsString(result);
	}
	
	@RequestMapping(value = "select", method = RequestMethod.POST, produces = "application/json")
	public String select(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		DeviceInfo param = mapper.readValue(json, DeviceInfo.class);
		List<DeviceInfo> result = springbootService.getDevices(param);
		return mapper.writeValueAsString(result);
	}
	
	@RequestMapping(value = "selectAll", method = RequestMethod.GET, produces = "application/json")
	public String selectAll() throws JsonProcessingException {
		List<DeviceInfo> result = springbootService.getAllDevices();
		return mapper.writeValueAsString(result);
	}
	
	@RequestMapping(value = "selectByPage", method = RequestMethod.POST, produces = "application/json")
	public String selectByPage(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		DeviceInfo param = mapper.readValue(json, DeviceInfo.class);
		List<DeviceInfo> result = springbootService.getPageDevices(param);
		return mapper.writeValueAsString(result);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	public void update(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		DeviceInfo param = mapper.readValue(json, DeviceInfo.class);
		springbootService.updateDevice(param);
	}
	
	@RequestMapping(value = "count", method = RequestMethod.GET)
	public int count() throws JsonParseException, JsonMappingException, IOException {
		return springbootService.getDeviceCount();
	}

//	@RequestMapping(value = "hello", method = RequestMethod.GET, produces = "application/json")
//	public String hello(String phone) throws JsonProcessingException {
//		return springbootService.hello(phone);
//	}
//
//	@RequestMapping(value = "getMaxId", method = RequestMethod.GET)
//	public Long getMaxId() throws JsonProcessingException {
//		return springbootService.getMaxId();
//	}
//
//	@RequestMapping(value = "getByPhone", method = RequestMethod.GET, produces = "application/json")
//	public String getByPhone(String phone) throws JsonProcessingException {
//		UserRegisterInfo user = springbootService.getByPhone(phone);
//		user.setName(defaultName);
//		return user.toJSON();
//	}
//
//	@RequestMapping(value = "getByPage", method = RequestMethod.GET, produces = "application/json")
//	public String getByPage(String phone) throws JsonProcessingException {
//		List<UserRegisterInfo> user = springbootService.getByPage(0, 15);
//		return new ObjectMapper().writeValueAsString(user);
//	}

//	@RequestMapping(value = "getIdentityInfoByPhone", method = RequestMethod.GET, produces = "application/json")
//	public String getIdentityInfoByPhone(String phone) throws JsonProcessingException {
//		return springbootService.getIdentityInfoByPhone(phone);
//	}

//	@ApiOperation(authorizations = { @Authorization(value = "sss", scopes = {
//			@AuthorizationScope(scope = "query:identity:info", description = "允许查询个人信息") }) }, value = "查询个人信息")
//	@RequestMapping(value = "queryIdentityInfo", method = RequestMethod.POST, produces = "application/json")
//	public String queryIdentityInfo(
//			@RequestBody @ApiParam(value = "用户查询参数", required = true) UserRegisterInfo userRegisterInfo)
//			throws JsonProcessingException {
//		String phone = userRegisterInfo.getPhone();
//		return springbootService.getIdentityInfoByPhone(phone);
//	}
}
