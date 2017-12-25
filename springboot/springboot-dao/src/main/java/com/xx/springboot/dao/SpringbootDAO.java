package com.xx.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xx.springboot.model.BaseMapper;
import com.xx.springboot.model.DeviceInfo;

@Mapper
public interface SpringbootDAO extends BaseMapper<DeviceInfo> {

	Long getMaxId();
}
