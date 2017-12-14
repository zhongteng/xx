package com.xx.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dk3k.customer.dubbo.DubboCustomerInfoSpi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.page.PageMethod;
import com.xx.springboot.dao.SpringbootDAO;
import com.xx.springboot.model.UserRegisterInfo;

@Service
public class SpringbootService {
	
	@Autowired
	private SpringbootDAO springbootDAO;
	@Autowired
	private DubboCustomerInfoSpi customerSpi;
	
	public String hello(String phone) throws JsonProcessingException {
		return new UserRegisterInfo(phone).toJSON();
	}
	
	public Long getMaxId() {
		return springbootDAO.getMaxId();
	}
	
	public UserRegisterInfo getByPhone(String phone) {
		return springbootDAO.selectOne(new UserRegisterInfo(phone));
	}
	
    public List<UserRegisterInfo> getByPage(int currentPage, int pageSize) {
    	PageMethod.startPage(currentPage, pageSize);
        List<UserRegisterInfo> docs = springbootDAO.selectAll();
//        springbootDAO.selectByRowBounds(record, new RowBounds(0, 15));
//        PageInfo<UserRegisterInfo> pageInfo = new PageInfo<UserRegisterInfo>(docs);
        return docs;
    }
    
    public String getIdentityInfoByPhone(String phone) throws JsonProcessingException {
    	return new ObjectMapper().writeValueAsString(customerSpi.pullIdentityInfo(customerSpi.queryUserIdByPhone(phone).getData()));
    }

}
