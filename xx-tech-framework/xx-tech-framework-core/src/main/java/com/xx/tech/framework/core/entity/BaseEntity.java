package com.xx.tech.framework.core.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Boolean logicDelete;
	
	private String createTime;
	
	private String updateTime;

}
