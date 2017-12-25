package com.xx.springboot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DeviceInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6886126988233213977L;

	private String deviceId;
	
	private String deviceMac;
	
	private String deviceType;
	
	private String deviceBrand;
	
	private String deviceSystem;
	
	public DeviceInfo() {
		
	}
	
	public DeviceInfo(String deviceId) {
		this.deviceId = deviceId;
	}
}
