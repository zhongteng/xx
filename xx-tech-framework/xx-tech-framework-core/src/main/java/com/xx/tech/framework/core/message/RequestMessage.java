package com.xx.tech.framework.core.message;

import com.xx.tech.framework.core.message.components.MessageBody;
import com.xx.tech.framework.core.message.components.MessageHead;
import com.xx.tech.framework.core.message.components.MessageTail;
import com.xx.tech.framework.core.message.enums.MessageType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RequestMessage extends Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String time;
	
	public RequestMessage() {
		
	}
	
	public RequestMessage(MessageHead head, MessageBody body, MessageTail tail) {
		super(head, body, tail);
	}
	
	public static RequestMessage newInstance() {
		MessageHead head = new MessageHead(MessageType.REQUEST);
		MessageBody body = new MessageBody();
		MessageTail tail = new MessageTail();
		return new RequestMessage(head, body, tail);
	}
	
}
