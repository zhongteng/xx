package com.xx.tech.framework.core.message.components;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String comment;
	
	private String content;
	
	public MessageBody() {
		
	}
	
	public MessageBody(String comment, String content) {
		this.comment = comment;
		this.content = content;
	}
	
}
