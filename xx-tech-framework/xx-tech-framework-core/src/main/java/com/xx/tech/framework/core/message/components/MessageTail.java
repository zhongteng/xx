package com.xx.tech.framework.core.message.components;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageTail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String channel;
	
	private String product;

	private String system;
	
	public MessageTail() {
		
	}
	
	public MessageTail(String channel, String product, String system) {
		this.channel = channel;
		this.product = product;
		this.system = system;
	}
	
}
