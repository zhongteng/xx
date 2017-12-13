package com.xx.springboot.dao;

import com.xx.springboot.model.UserRegisterInfo;

import tk.mybatis.mapper.common.Mapper;

public interface SpringbootDAO extends Mapper<UserRegisterInfo> {

	Long getMaxId();
}
