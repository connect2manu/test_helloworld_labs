package com.manu.demo.application.delegate;

import java.math.BigInteger;
import java.util.Date;

/**
 * Object to hold incoming request parameters for WriteNotification requests.
 * 
 * @author manu.mehrotra
 */
public class WriteRequestVO {

	private BigInteger macAddress;
	private String notificationType;
	private Date timestamp = null;

	public WriteRequestVO() {
	}

	public WriteRequestVO(BigInteger bigMACAddress, String notificationType, Date timestamp) {
		macAddress = bigMACAddress;
		this.notificationType = notificationType;
		this.timestamp = timestamp;
	}

	public BigInteger getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(BigInteger macAddress) {
		this.macAddress = macAddress;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WriteNotificationRequestData [macAddress=");
		builder.append(macAddress);
		builder.append(", notificationType=");
		builder.append(notificationType);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
