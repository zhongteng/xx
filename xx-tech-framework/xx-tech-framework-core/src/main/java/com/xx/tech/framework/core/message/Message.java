package com.xx.tech.framework.core.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.xx.tech.framework.core.message.components.MessageBody;
import com.xx.tech.framework.core.message.components.MessageHead;
import com.xx.tech.framework.core.message.components.MessageTail;

import lombok.Data;

@Data
public abstract class Message implements Cloneable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected MessageHead head;

	protected MessageBody body;

	protected MessageTail tail;
	
	public Message() {
		
	}
	
	public Message(MessageHead head) {
		this.head = head;
	}
	
	public Message(MessageHead head, MessageBody body) {
		this.head = head;
		this.body = body;
	}
	
	public Message(MessageHead head, MessageBody body, MessageTail tail) {
		this.head = head;
		this.body = body;
		this.tail = tail;
	}
	
	public Object clone() throws CloneNotSupportedException {
		Message message = (Message) super.clone();
		return message;
	}

	public Object deepClone() throws IOException, ClassNotFoundException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
	
}
