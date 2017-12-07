package com.xx.tech.framework.core.message;

import com.xx.tech.framework.core.message.enums.MessageType;

/**
 * 消息工厂类
 * 提供：	普通工厂方法
 * 		静态工厂方法
 * 		TODO - 抽象工厂方法
 * @author benjamin
 *
 */
public class MessageFactory {

	private static final Object synLock = new Object();

	private static MessageFactory instance = null;

	private MessageFactory() {

	}

	public static MessageFactory getInstance() {
		if (instance == null) {
			synchronized (synLock) {
				if (instance == null) {
					instance = new MessageFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * Message普通工厂方法
	 * @param messageType
	 * @return
	 */
	public Message produce(MessageType messageType) {
		Message message = null;
		if (MessageType.REQUEST.equals(messageType)) {
			message = RequestMessage.newInstance();
		} else if (MessageType.RESPONSE.equals(messageType)) {
			message = ResponseMessage.newInstance();
		}
		return message;
	}
	
	/**
	 * RequestMessage静态工厂方法
	 * @return
	 */
	public static RequestMessage produceRequest() {
		return RequestMessage.newInstance();
	}
	
	/**
	 * ResponseMessage静态工厂方法
	 * @return
	 */
	public static ResponseMessage produceResponse() {
		return ResponseMessage.newInstance();
	}
	
}
