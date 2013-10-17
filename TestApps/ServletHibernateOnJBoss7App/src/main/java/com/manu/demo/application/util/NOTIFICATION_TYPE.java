package com.manu.demo.application.util;

/**
 * @author manu.mehrotra
 */
public enum NOTIFICATION_TYPE {
	REMINDER("REMINDER"), MESSAGE("MESSAGE");
	
	/**
	 * Represents the String value of the enum constant.
	 */
	String name;
	
	private NOTIFICATION_TYPE(String type) {
		name = type;
	}

	/**
	 * Returns the String value of the enum constant.
	 * 
	 * @return the notificationType
	 */
	public String getName() {
		return name;
	}
}
