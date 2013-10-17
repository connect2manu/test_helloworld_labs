package com.manu.demo.application.delegate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.AppStartupService;
import com.manu.demo.application.al.exceptions.BusinessException;
import com.manu.demo.application.al.exceptions.InfrastructureException;
import com.manu.demo.application.dba.NotificationDAO;
import com.manu.demo.application.model.Notification;
import com.manu.demo.application.util.AppUtils;
import com.manu.demo.application.util.NOTIFICATION_TYPE;
import com.manu.demo.xml.NotificationServiceResponse;
import com.manu.demo.xml.NotificationServiceResponse.NotificationList;
import com.manu.demo.xml.ObjectFactory;

/**
 * Responsible for retrieving the filtered list of notifications for a specific MAC.
 * 
 * @author manu.mehrotra
 */
public class GetNotificationHelper {

	private final Logger logger = LoggerFactory.getLogger(GetNotificationHelper.class);

	/**
	 * 1. Retrieves the Notifications for the MacAddress. <br>
	 * 2. Generates the xml. <br>
	 * 3. Deletes (only soft delete) all the notifications for the given MAC. <br>
	 * 4. Returns the xml response string. <br>
	 * 
	 * @param bigMacAddress
	 * @return xml string
	 * @throws BusinessException
	 */
	public String process(BigInteger bigMacAddress) throws InfrastructureException, BusinessException {
		logger.debug("+ process()");

		NotificationDAO notificationDAO = new NotificationDAO();
		Notification notification = notificationDAO.getNotificationByMac(bigMacAddress);
		String xml = null;
		if (notification != null) {
			xml = generateXML(notification);
			notificationDAO.executeDelete(bigMacAddress, notification);
			logger.debug("- process()");
			return xml;
		} else {
			String mac = AppUtils.convertBigIntToString(bigMacAddress);
			logger.error("No entry/mapping found for MAC Address ["+mac+"] = " + bigMacAddress);
			return generateXML(null); // artf81068 :: Return empty notification server response list.
		}
	}

	/**
	 * Returns the generated xml.
	 * 
	 * @param notification
	 * @return
	 */
	public String generateXML(Notification notification) throws InfrastructureException {
		logger.debug("Generating notifications list XML...");

		ObjectFactory factory = new ObjectFactory();
		NotificationServiceResponse response = factory.createNotificationServiceResponse();
		NotificationList notificationListElement = factory.createNotificationServiceResponseNotificationList();
		if(notification != null) {
			notificationListElement = populateNotificationXML(notification, notificationListElement);
		} else {
			logger.warn("Generating empty response for non-existent MAC Address.");
		}
		response.setNotificationList(notificationListElement);
		OutputStream outStream = new ByteArrayOutputStream();
		String generatedXML;
		
		try {
			JAXBContext jaxbContext = AppStartupService.getInstance().getJaxbContext();
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(response, outStream);
			
			generatedXML = outStream.toString();
		} 
		catch (JAXBException jaxbEx) {
			if(logger.isTraceEnabled()) {
				jaxbEx.printStackTrace();
			}
			logger.error("Error occured during JAXB Marshalling. "+jaxbEx.getMessage());
			throw new InfrastructureException("Error occured during XML generation.", jaxbEx);
		}
		catch (Exception ex) {
			if(logger.isTraceEnabled()) {
				ex.printStackTrace();
			}
			logger.error("Unable to generate XML: "+ex.getMessage());
			throw new InfrastructureException("Unable to generate XML.", ex);
		} finally {
			if(outStream != null) {
				try {
					outStream.close();
				} catch (IOException ioe) {
					logger.error("Error occured while closing output stream. "+ioe.getMessage());
					throw new InfrastructureException("Error occured while closing output stream.", ioe);
				}
			}
		}

		logger.debug("- generateXML()");
		
		return generatedXML;
	}

	/**
	 * Generates the generated xml inner structure.
	 * 
	 * @param notification
	 * @param notificationList
	 * @return
	 */
	private NotificationList populateNotificationXML(Notification notification, NotificationList notificationList) throws InfrastructureException {
		logger.debug("+ populateNotificationXML()");

		NotificationServiceResponse.NotificationList.Notification notificationElement = null;
		List<NotificationServiceResponse.NotificationList.Notification> availableNotifications = notificationList
				.getNotification();

		Date expiryDate = getExpiryDate();

		logger.debug("populateNotificationXML() | Parsing Notification [MAC= "
				+ AppUtils.convertBigIntToString(notification.getMacaddress()) + "] :: " + notification.toString());

		if (notification.getReminder() != null && isUnExpired(notification.getReminder(), expiryDate)) {
			notificationElement = new NotificationServiceResponse.NotificationList.Notification();
			notificationElement.setNotificationType(NOTIFICATION_TYPE.REMINDER.getName());
			notificationElement.setTimeStamp(new BigInteger(getUnixTimestampInSecs(notification.getReminder())));
			logger.debug("populateNotificationXML() | Adding NotificationType = " + NOTIFICATION_TYPE.REMINDER.getName()
					+ " with Timestamp = " + notification.getReminder().getTime() + " in NotificationList.");
			availableNotifications.add(notificationElement);
		}

		if (notification.getMessage() != null && isUnExpired(notification.getMessage(), expiryDate)) {
			notificationElement = new NotificationServiceResponse.NotificationList.Notification();
			notificationElement.setNotificationType(NOTIFICATION_TYPE.MESSAGE.getName());
			notificationElement.setTimeStamp(new BigInteger(getUnixTimestampInSecs(notification.getMessage())));
			logger.debug("populateNotificationXML() | Adding NotificationType = " + NOTIFICATION_TYPE.MESSAGE.getName()
					+ " with Timestamp = " + notification.getMessage().getTime() + " in NotificationList.");
			availableNotifications.add(notificationElement);
		}

		logger.debug("- populateNotificationXML()");
		return notificationList;
	}

	/**
	 * Returns the string representation of unix timestamp epoch in seconds.
	 * 
	 * @param date
	 * @return
	 */
	private String getUnixTimestampInSecs(Date date) {
		return String.valueOf(date.getTime() / 1000L);
	}

	/**
	 * Returns the expiry date, based upon the configured expiry hours from the
	 * current time.
	 * 
	 * @return
	 */
	private Date getExpiryDate() {
		int hours = AppStartupService.getInstance().getNotificationExpiryHours();
		logger.debug("+ getExpiryDate() | Configured Notification Expiry hours = " + hours);
		GregorianCalendar expiryDate = new GregorianCalendar();
		expiryDate.setTimeInMillis(System.currentTimeMillis());
		expiryDate.add(Calendar.HOUR, -hours);
		return expiryDate.getTime();
	}

	/**
	 * Returns true if the given notification timestamp has not yet expired.
	 * 
	 * @param notificationTime
	 * @param expiryTime
	 * @return
	 */
	private boolean isUnExpired(Date notificationTime, Date expiryTime) {
		logger.debug("+ isUnExpired() | Notification - TimeStamp = " + notificationTime + " , expiryTime = " + expiryTime);
		if (notificationTime.compareTo(expiryTime) > 0) {
			logger.debug("isUnExpired()() | This notification TimeStamp (" + notificationTime.getTime()
					+ ") falls after expiryTime("
					+ expiryTime.getTime() + ")");
			return true;
		}
		return false;
	}

}
