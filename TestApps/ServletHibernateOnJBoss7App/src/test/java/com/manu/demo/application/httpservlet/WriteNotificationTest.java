/**
 * 
 */
package com.manu.demo.application.httpservlet;

import java.math.BigInteger;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.manu.demo.application.MockNotificationData;
import com.manu.demo.application.delegate.WriteRequestVO;
import com.manu.demo.application.util.AppUtils;

/**
 */
public class WriteNotificationTest extends TestCase {

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
	 * Tests writeNotification insert and update.
	 */
	@Test
	public final void testWriteNotification() {
		BigInteger biMACAddress = AppUtils.validateAndConvertMAC(MockNotificationData.MAC2);
		String timestampStr = String.valueOf(System.currentTimeMillis());
		
		Date date = new Date(AppUtils.validateAndConvertStringToTimestamp(timestampStr));
		
		WriteRequestVO requestData = new WriteRequestVO(biMACAddress, "REMINDER", date);
		assertSame("REMINDER", requestData.getNotificationType());
		
		// write notification request for first time
		boolean isInserted = MockNotificationData.getInstance().addNotification(requestData);
		assertTrue(isInserted); // test notification insert
		
		// write notification request for second time
		requestData.setNotificationType("MESSAGE");
		isInserted = MockNotificationData.getInstance().addNotification(requestData);
		assertFalse(isInserted); // test notification update
	}
}
