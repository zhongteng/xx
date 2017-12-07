package com.xx.tech.framework.core.message.enums;

public enum MessageType {

	REQUEST("请求"),RESPONSE("响应");
	
	private String describe;
	
	private MessageType(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
