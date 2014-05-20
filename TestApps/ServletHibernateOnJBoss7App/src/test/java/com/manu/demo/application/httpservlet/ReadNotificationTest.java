/**
 * 
 */
package com.manu.demo.application.httpservlet;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.manu.demo.application.MockNotificationData;
import com.manu.demo.application.al.AppStartupService;
import com.manu.demo.application.delegate.GetNotificationHelper;
import com.manu.demo.application.delegate.WriteRequestVO;
import com.manu.demo.application.model.Notification;
import com.manu.demo.application.util.AppUtils;

/**
 */
public class ReadNotificationTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests getNotifications response xml generation.
	 */
	@Test
	public final void testGetNotifications() {
		BigInteger bigMACAddress = AppUtils
				.convertStringToBigInt(MockNotificationData.MAC1);

		Notification notification1 = MockNotificationData.getInstance().getNotificationByMac(
				bigMACAddress);
		assertNotNull(notification1);

		GetNotificationHelper readProcessor = new GetNotificationHelper();
		String xml = readProcessor.generateXML(notification1);
		assertNotNull(xml);
	}
	
	/**
	 * Tests getNotifications filtered notifications response.
	 */
	@Test
	public final void testFilteredGetNotifications() {
		BigInteger bigMACAddress = AppUtils
				.convertStringToBigInt(MockNotificationData.MAC3);
		String timestampStr = String.valueOf(System.currentTimeMillis());
		
		/*
		 * Adding a notification with current timestamp.
		 */
		Date date = new Date(AppUtils.validateAndConvertStringToTimestamp(timestampStr));
		WriteRequestVO notificationWithCurrentTimestamp = new WriteRequestVO(
bigMACAddress, "REMINDER", date);
		MockNotificationData.getInstance().addNotification(notificationWithCurrentTimestamp);

		/*
		 * Adding a notification with expired timestamp.
		 */
		GregorianCalendar expiryDate = new GregorianCalendar();
		expiryDate.setTimeInMillis(System.currentTimeMillis());
		expiryDate.add(Calendar.HOUR, -AppStartupService.getInstance().getNotificationExpiryHours());
		WriteRequestVO notificationWithExpiredTimestamp = new WriteRequestVO(
bigMACAddress, "MESSAGE",
				expiryDate.getTime());
		MockNotificationData.getInstance().addNotification(notificationWithExpiredTimestamp);
		
		Notification notification = MockNotificationData.getInstance().getNotificationByMac(
				bigMACAddress);
		assertNotNull(notification);

		/*
		 * Generates xml of the notification having one expired and one non-expired timestamp.
		 */
		GetNotificationHelper readProcessor = new GetNotificationHelper();
		String xml = readProcessor.generateXML(notification);
		assertNotNull(xml);
		
		/*
		 * Verify that unexpired S_SUBSCRIBER_INFO notification update is available.
		 */
		int indexOfSubsInfo = xml.indexOf("REMINDER");
		assertNotSame(-1, indexOfSubsInfo);
		
		/*
		 * Verify that expired S_MESSAGE_INFO notification is filtered in generated response xml.
		 */
		int indexOfMsgInfo = xml.indexOf("MESSAGE");
		assertEquals(-1, indexOfMsgInfo);
	}
	
	/**
	 * Tests getNotifications notifications cleanup after xml response generation.
	 */
	@Test
	public final void testGetNotificationsCleanup() {

		BigInteger bigMACAddress = AppUtils
				.convertStringToBigInt(MockNotificationData.MAC1);

		Notification notification1 = MockNotificationData.getInstance().getNotificationByMac(
				bigMACAddress);
		assertNotNull(notification1);

		GetNotificationHelper readProcessor = new GetNotificationHelper();
		String responseBeforeCleanup = readProcessor.generateXML(notification1);
		
		assertNotNull(responseBeforeCleanup);
		assertNotSame(-1, responseBeforeCleanup.indexOf("REMINDER"));
		assertNotSame(-1, responseBeforeCleanup.indexOf("MESSAGE"));
		
		MockNotificationData.getInstance().clearNotification(notification1);
		String responseAfterCleanup = readProcessor.generateXML(notification1);
		assertNotNull(responseAfterCleanup);
		
		assertNotSame(responseBeforeCleanup, responseAfterCleanup);
		assertSame(-1, responseAfterCleanup.indexOf("REMINDER"));
		assertSame(-1, responseAfterCleanup.indexOf("MESSAGE"));
	}

}
