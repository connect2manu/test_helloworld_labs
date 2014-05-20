/**
 * 
 */
package com.manu.demo.application.util;

import java.math.BigInteger;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.manu.demo.application.al.exceptions.BusinessException;

/**
 */
public class AppUtilsTest extends TestCase {

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

	@Test
	public final void testValidMAC() {
		String macAddress = "0014F8E3666D";
		assertNotNull(AppUtils.validateAndConvertMAC(macAddress));
	}

	@Test(expected = BusinessException.class)
	public final void testInvalidMAC() {
		// MAC Can not have char 'G'. Should be hex supported char only.
		String invalidMacAddress = "0014F8E3666G";

		try {
			AppUtils.validateAndConvertMAC("");
			AppUtils.validateAndConvertMAC(invalidMacAddress);
			
			fail("Should throw exception - Invalid MACAddress.");
		} catch (BusinessException be) {
			// expected, it's all good
		} catch (Exception ex) {
			fail("Should throw BusinessException only.");
		}
	}

	@Test
	public final void testValidNotificationTypes() {
		// Positive tests
		assertEquals(true, AppUtils.isValidNotificationType("REMINDER"));
		assertEquals(true, AppUtils.isValidNotificationType("MESSAGE"));
	}

	@Test
	public final void testInvalidNotificationType() {
		// Negative tests
		assertEquals(false, AppUtils.isValidNotificationType(null));
		assertEquals(false, AppUtils.isValidNotificationType("SomeNotificationType"));
	}

	@Test
	public final void testValidTimestamp() {
		String timestamp10CharsTestValue = "1361772367";
		long timestamp13CharsTestValue = System.currentTimeMillis();
		
		assertEquals("1361772367000", String.valueOf(AppUtils
				.validateAndConvertStringToTimestamp(timestamp10CharsTestValue)));
		
		assertEquals(timestamp13CharsTestValue,
				AppUtils.validateAndConvertStringToTimestamp(String
						.valueOf(timestamp13CharsTestValue)));
	}

	@Test(expected = BusinessException.class)
	public final void testInvalidTimestamp() {
		String invalidTimestamp = "136177236A";
		
		try {
			AppUtils.validateAndConvertStringToTimestamp(invalidTimestamp);
			
			fail("Should throw exception - Invalid TimeStamp.");
		} catch (BusinessException be) {
			// expected, it's all good
		} catch (Exception ex) {
			fail("Should throw BusinessException only.");
		}
	}

	@Test
	public final void testMACConversions() {
		String macAddress = "0014F8E3666D";
		BigInteger encodedMAC1 = AppUtils.convertStringToBigInt(macAddress);
		String decodedMAC = AppUtils.convertBigIntToString(encodedMAC1);
		BigInteger encodedMAC2 = AppUtils.convertStringToBigInt(decodedMAC);

		assertEquals(macAddress, decodedMAC);
		assertEquals(encodedMAC1, encodedMAC2);
	}

}
