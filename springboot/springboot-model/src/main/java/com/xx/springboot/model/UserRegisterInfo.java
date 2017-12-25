package com.xx.springboot.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用户注册信息")
@Data
public class UserRegisterInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5756192962061349279L;

	@ApiModelProperty(value = "用户名", name = "name")
	@Transient
	private String name;

	/** 客户号 **/
	private Long userId;

	/** 注册手机 **/
	@ApiModelProperty(value = "手机号", name = "phone", required = true)
	private String phone;

	/** 注册密码 **/
	private String password;

	/** 微信绑定号 **/
	private String openId;

	/** 注册时间 **/
	private String regTime;

	/** 注册来源渠道 **/
	private String fromChannel;

	/** 注册来源产品 **/
	private String fromProduct;

	/** 下载渠道 **/
	private String downloadChannel;

	public UserRegisterInfo() {

	}

	public UserRegisterInfo(String phone) {
		this.phone = phone;
	}

	public String toJSON() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}
}
