package com.manu.demo.application;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.manu.demo.application.delegate.WriteRequestVO;
import com.manu.demo.application.model.Notification;
import com.manu.demo.application.util.AppUtils;
import com.manu.demo.application.util.NOTIFICATION_TYPE;

/**
 * Contains mock Notification's and used in test cases for Notification data manipulation.
 */
public class MockNotificationData {

	private static MockNotificationData mockData = null;

	public static final String MAC1 = "00123456789A";
	public static final String MAC2 = "00123456789B";
	public static final String MAC3 = "00123456789C";

	private final List<Notification> notificationData = new ArrayList<Notification>();

	/**
	 * 
	 */
	private MockNotificationData() {
		Date date1 = new Date(System.currentTimeMillis());

		Notification notification1 = new Notification();
		// Adding a notification of for MAC1(00123456789A).
		notification1.setMacaddress(AppUtils.convertStringToBigInt(MAC1));
		// Setting all 13 types of notifications.
		notification1.setMessage(date1);
		notification1.setReminder(date1);

		notificationData.add(notification1);
	}

	public Notification getNotificationByMac(BigInteger macAddress) {
		for (Notification notification : notificationData) {
			if (notification.getMacaddress().equals(macAddress)) {
				return notification;
			}
		}
		return null;
	}

	/**
	 * @param trggrData
	 */
	public boolean addNotification(WriteRequestVO trggrData) {
		BigInteger mac = trggrData.getMacAddress();

		boolean isNew = false;

		Notification existingNotification = getNotificationByMac(mac);
		if (existingNotification != null) { // update existing notification
			updateNotification(existingNotification, trggrData);
			isNew = false;
		} else { // insert new notification
			Notification newNotification = new Notification();
			newNotification.setMacaddress(mac);
			newNotification = updateNotification(newNotification, trggrData);
			notificationData.add(newNotification);
			isNew = true;
		}

		return isNew;
	}

	/**
	 * @param trggr
	 */
	public void removeNotification(Notification trggr) {
		if (notificationData.contains(trggr)) {
			notificationData.remove(trggr);
		}
	}

	/**
	 * @return
	 */
	public List<Notification> getNotifications() {
		return notificationData;
	}

	/**
	 * @return
	 */
	public static MockNotificationData getInstance() {
		if (mockData == null) {
			mockData = new MockNotificationData();
		}
		return mockData;
	}

	/**
	 * @param notification
	 * @param notificationData
	 * @return
	 */
	private Notification updateNotification(Notification notification,
			WriteRequestVO notificationData) {

		if (notification == null || notificationData == null
				|| notificationData.getNotificationType() == null) {
			return null;
		}

		switch (NOTIFICATION_TYPE.valueOf(notificationData.getNotificationType())) {
		case REMINDER:
			notification.setReminder(notificationData.getTimestamp());
			break;
		case MESSAGE:
			notification.setMessage(notificationData.getTimestamp());
			break;
		default:
			return null;
		}

		return notification;
	}

	/**
	 * @param notification
	 */
	public void clearNotification(Notification notification) {
		if (notification != null) {
			notification.setMessage(null);
			notification.setReminder(null);
		}
	}

}
