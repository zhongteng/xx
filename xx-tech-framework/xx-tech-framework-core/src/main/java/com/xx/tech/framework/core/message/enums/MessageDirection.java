package com.xx.tech.framework.core.message.enums;

public enum MessageDirection {

	INBOUND("呼入"),OUTBOUND("呼出");
	
	private String describe;
	
	private MessageDirection(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
