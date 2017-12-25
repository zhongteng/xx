package com.xx.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xx.springboot.dao.SpringbootDAO;
import com.xx.springboot.model.DeviceInfo;

@Service
public class SpringbootService {
	
	@Autowired
	private SpringbootDAO springbootDAO;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public List<DeviceInfo> getDevices(DeviceInfo param) {
		return springbootDAO.select(param);
	}
	
	public List<DeviceInfo> getAllDevices() {
		return springbootDAO.selectAll();
	}
	
	public DeviceInfo getDevice(DeviceInfo param) {
		return springbootDAO.selectOne(param);
	}
	
	public List<DeviceInfo> getPageDevices(DeviceInfo param) {
		int pageNum = param.getPageNum();
		int pageSize = param.getPageSize();
		Page<DeviceInfo> page = PageHelper.startPage(pageNum, pageSize);
		page.addAll(springbootDAO.selectAll());
		PageInfo<DeviceInfo> info = new PageInfo<>(page);
		return info.getList();
	}
	
	public int getDeviceCount() {
		return springbootDAO.selectCount(null);
	}
	
	public void insertDevice(DeviceInfo device) {
		System.out.println(this.mongoTemplate.getDb().getName());
		this.mongoTemplate.save(device);
		this.redisTemplate.opsForValue().set("deviceId", device.getDeviceId());
		try {
			int count = springbootDAO.insert(device);
			System.out.println("影响条目数：" + count);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常：" + e.getMessage());
		}
	}
	
	public void batchInsertDevices(List<DeviceInfo> deviceList) {
		int count = springbootDAO.insertList(deviceList);
		System.out.println("影响条目数：" + count);
	}
	
	public void updateDevice(DeviceInfo param) {
		int count = springbootDAO.updateByPrimaryKey(param);
		System.out.println("影响条目数：" + count);
	}
	
//	public void allMethod() {
//		springbootDAO.select(arg0);
//		springbootDAO.selectAll();
//		springbootDAO.selectByExample(arg0);
//		springbootDAO.selectByExampleAndRowBounds(arg0, arg1);
//		springbootDAO.selectByPrimaryKey(arg0);
//		springbootDAO.selectByRowBounds(arg0, arg1);
//		springbootDAO.selectCount(arg0);
//		springbootDAO.selectCountByExample(arg0);
//		springbootDAO.selectOne(arg0);
//		
//		springbootDAO.insert(arg0);
//		springbootDAO.insertList(arg0)
//		springbootDAO.insertSelective(arg0);
//		springbootDAO.insertUseGeneratedKeys(arg0);
//		
//		springbootDAO.updateByExample(arg0, arg1);
//		springbootDAO.updateByExampleSelective(arg0, arg1);
//		springbootDAO.updateByPrimaryKey(arg0);
//		springbootDAO.updateByPrimaryKeySelective(arg0);
//		
//		springbootDAO.delete(arg0);
//		springbootDAO.deleteByExample(arg0);
//		springbootDAO.deleteByPrimaryKey(arg0);
//	}
	
	

}
