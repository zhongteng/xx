package com.xx.springboot.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6864259276900262985L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String createTime;
	
	private String updateTime;
	
	private int logicDelete = 0;
	
	@Transient
	protected int pageNum;
	
	@Transient
	protected int pageSize;
}
