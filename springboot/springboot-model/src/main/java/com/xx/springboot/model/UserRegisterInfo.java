package com.xx.springboot.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class UserRegisterInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5756192962061349279L;
	
	@Transient
	private String name;

	/** 客户号 **/
	protected Long userId;

	/** 注册手机 **/
	protected String phone;

	/** 注册密码 **/
	protected String password;

	/** 微信绑定号 **/
	protected String openId;

	/** 注册时间 **/
	protected String regTime;

	/** 注册来源渠道 **/
	protected String fromChannel;

	/** 注册来源产品 **/
	protected String fromProduct;

	/** 下载渠道 **/
	protected String downloadChannel;

	public UserRegisterInfo() {

	}

	public UserRegisterInfo(String phone) {
		this.phone = phone;
	}

	public String toJSON() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}
}