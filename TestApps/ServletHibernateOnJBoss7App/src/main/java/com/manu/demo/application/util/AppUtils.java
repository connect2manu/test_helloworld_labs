package com.manu.demo.application.util;

import java.math.BigInteger;

import com.manu.demo.application.al.exceptions.BusinessException;

/**
 * General purpose util class used for conversions.
 * 
 * @author manu.mehrotra
 * 
 */
public class AppUtils {

	/**
	 * 
	 */
	private AppUtils() {
	}

	/**
	 * Converts the passed string MACAddess into the radix 16 based BigInterger.
	 * 
	 * @param macAddress
	 * @return
	 */
	public static BigInteger convertStringToBigInt(String macAddress)
			throws NumberFormatException {
		if (macAddress == null) {
			throw new IllegalStateException("MacAddress should be not null");
		}

		BigInteger bi = new BigInteger(macAddress, 16);
		// BigInteger bi = new BigInteger(macAddress.getBytes());
		return bi;
	}

	/**
	 * Decodes the given bigInt MAC back to the original MAC string.
	 * 
	 * @param bigInt
	 * @return
	 */
	public static String convertBigIntToString(BigInteger bigInt) {
		if (bigInt == null) {
			throw new IllegalStateException("MacAddress should be not null.");
		}

		final int MAC_MAX_LENGTH = 12;
		String decodedMAC = bigInt.toString(16);

		int lenght = decodedMAC.length();
		if (lenght < MAC_MAX_LENGTH) {
			int offest = MAC_MAX_LENGTH - lenght;
			for (int count = 1; count <= offest; count++) {
				decodedMAC = "0" + decodedMAC;
			}
		}
		return decodedMAC.toUpperCase();
	}

	/**
	 * Validates the input MAC string, throws exception if its invalid else
	 * returns the bigint converted MAC.
	 * 
	 * @param macAddress
	 * @return
	 * @throws BusinessException
	 */
	public static BigInteger validateAndConvertMAC(String macAddress)
			throws BusinessException {
		if (macAddress == null) {
			throw new BusinessException("Missing parameter MACAddress");
		}
		if ("".equals(macAddress.trim())) {
			throw new BusinessException("Empty MACAddress");
		}
		BigInteger bigMACAddress = null;
		try {
			bigMACAddress = convertStringToBigInt(macAddress);
		} catch (NumberFormatException e) {
			throw new BusinessException("Invalid MACAddress - " + macAddress);
		}

		return bigMACAddress;
	}

	/**
	 * Returns true if the specified notification type is valid.
	 * 
	 * @param notificationType
	 * @return
	 */
	public static boolean isValidNotificationType(String notificationType) {
		if (notificationType == null || "".equals(notificationType)) {
			return false;
		}
		NOTIFICATION_TYPE trgrType = null;
		try {
			trgrType = NOTIFICATION_TYPE.valueOf(notificationType);
		} catch (IllegalArgumentException ex) {
			return false;
		}

		switch (trgrType) {
		case REMINDER:
			return true;
		case MESSAGE:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Validates the provided string timestamp and returns the unix timestamp in
	 * millis.
	 * 
	 * @param timestampStr
	 * @return
	 * @throws BusinessException
	 */
	public static long validateAndConvertStringToTimestamp(String timestampStr)
			throws BusinessException {

		if (timestampStr == null) {
			throw new BusinessException("Missing parameter TimeStamp");
		}

		if ("".equals(timestampStr.trim())) {
			throw new BusinessException("Empty TimeStamp");
		}

		return convertStringToTimestamp(timestampStr);
	}

	/**
	 * Takes the string timestamp and returns the unix timestamp in millis.
	 * 
	 * @param timestampStr
	 * @return
	 * @throws BusinessException
	 */
	public static long convertStringToTimestamp(String timestampStr) throws BusinessException {

		/*
		 * Unix timestamp has to be in 4 to 13 digits long, else MySql will throw invalid date/time error.
		 */
		long timestamp;

		try {
			// 10 digit means - Unix epoch time in seconds.
			// Return timestamp in millis, after converting the seconds to
			// millis to make a valid Date.
			if (timestampStr.length() == 10) {
				return Long.valueOf(timestampStr) * 1000L;
			}

			timestamp = Long.parseLong(timestampStr);
		} catch (NumberFormatException nfe) {
			throw new BusinessException("Invalid TimeStamp - " + timestampStr, nfe);
		}

		return timestamp;
	}
	
}
