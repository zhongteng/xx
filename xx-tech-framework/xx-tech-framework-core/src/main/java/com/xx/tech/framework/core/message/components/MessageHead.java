package com.xx.tech.framework.core.message.components;

import java.io.Serializable;

import com.xx.tech.framework.core.message.enums.MessageDirection;
import com.xx.tech.framework.core.message.enums.MessageType;

import lombok.Data;

@Data
public class MessageHead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long traceId;
	
	private String service;
	
	private String security;
	
	private MessageType type;
	
	private MessageDirection direction;
	
	public MessageHead() {
		
	}
	
	public MessageHead(MessageType type) {
		this.type = type;
	}
	
	public MessageHead(MessageType type, MessageDirection direction) {
		this.type = type;
		this.direction = direction;
	}

}
