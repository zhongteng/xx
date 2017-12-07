package com.xx.tech.framework.core.message;

import com.xx.tech.framework.core.message.components.MessageBody;
import com.xx.tech.framework.core.message.components.MessageHead;
import com.xx.tech.framework.core.message.components.MessageTail;
import com.xx.tech.framework.core.message.enums.MessageType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String describe;
	
	private String code;
	
	private String costs;
	
	public ResponseMessage() {
		
	}
	
	public ResponseMessage(MessageHead head, MessageBody body, MessageTail tail) {
		super(head, body, tail);
	}

	public static ResponseMessage newInstance() {
		MessageHead head = new MessageHead(MessageType.RESPONSE);
		MessageBody body = new MessageBody();
		MessageTail tail = new MessageTail();
		return new ResponseMessage(head, body, tail);
	}

}
