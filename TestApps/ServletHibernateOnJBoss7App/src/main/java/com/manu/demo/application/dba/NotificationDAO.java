package com.manu.demo.application.dba;

import java.math.BigInteger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.exceptions.BusinessException;
import com.manu.demo.application.al.exceptions.InfrastructureException;
import com.manu.demo.application.delegate.WriteRequestVO;
import com.manu.demo.application.model.Notification;
import com.manu.demo.application.util.NOTIFICATION_TYPE;

/**
 * A typical DAO for persistence and retrieval of Notification(s) using Hibernate.
 * 
 * @author manu.mehrotra
 */
public class NotificationDAO extends BaseBusinessObject {

	private final Logger logger = LoggerFactory.getLogger(NotificationDAO.class);

	/**
	 * Default constructor
	 */
	public NotificationDAO() {

	}

	/**
	 * @param macAddress
	 * @return
	 */
	public Notification loadNotification(Session session, BigInteger macAddress) {
		Notification trgr = (Notification) session.get(Notification.class, macAddress);
		logger.info("loadNotification | notification = " + trgr);
		return trgr;
	}
	
	/**
	 * Retrieves and returns the Notification record based on the MACAddress from
	 * persistent store.
	 * 
	 * @param mac
	 * @return
	 * @throws InfrastructureException
	 */
	public Notification getNotificationByMac(BigInteger mac)
			throws InfrastructureException {
		logger.info("+ getNotificationByMac()");
		Notification notification = null;

		try {
			notification = loadNotification(getSession(), mac);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			closeSession();
		}
		logger.info("- getNotificationByMac()");

		return notification;
	}
	
	/**
	 * Performs both Load and SaveOrUpdate atomic operation of the Notification within 
	 * the same session. 
	 * 
	 * Saves or updates (if already exist) the passed Notification object to
	 * persistent store.
	 * 
	 * @param notificationData
	 * @return
	 */
	public boolean loadAndSaveNotification(WriteRequestVO notificationData) throws InfrastructureException {
		logger.info("+ loadAndSaveNotification()");

		Notification notification = null;
		boolean isNew = false;

		try {
			Session session = getSession();
			beginTransaction(session);
			notification = loadNotification(session, notificationData.getMacAddress());

			if (notification == null) {
				notification = new Notification();
				notification.setMacaddress(notificationData.getMacAddress());
				isNew = true;
			}
			notification = updateNotification(notification, notificationData);
			
			session.saveOrUpdate(notification);
			logger.info("++++ saveOrUpdate() | notification :: "+ notification.toString());
			
			if(logger.isDebugEnabled()) {
				Notification trgr = loadNotification(session, notification.getMacaddress());
				logger.debug("saveOrUpdate Check | Input MAC = "+notification.getMacaddress()+", GetMacFromPersistantNotification = "+trgr.getMacaddress());
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			commit();
		}
		logger.info("- loadAndSaveNotification()");
		return isNew;
	}

	/**
	 * Makes soft deletion of the record into persistent store corresponding to
	 * the given MACAddress by setting all notification type timestamps to null and
	 * also removes the cached data in session.
	 * 
	 * @param bigMacAddress
	 * @param cachedNotification
	 */
	public void executeDelete(BigInteger bigMacAddress, Notification cachedNotification) {
		logger.info("+ executeDelete()");
		try {
			Session session = getSession();
			beginTransaction(session);
			Notification notificationObj = loadNotification(session, bigMacAddress);

			if (notificationObj != null) {
				if(logger.isDebugEnabled()) {
					logger.debug("+ executeDelete() | Clearing Notifications for = "
							+ notificationObj.toString());
				}
				clearNotification(notificationObj);
			}
			clearNotification(cachedNotification);
		} catch (HibernateException ex) {
			rollback();
			throw new InfrastructureException(ex);
		} finally {
			commit();
		}
		logger.info("+ executeDelete()");
	}

	/**
	 * @param notification
	 */
	private void clearNotification(Notification notification) {
		if (notification != null) {
			notification.setMessage(null);
			notification.setReminder(null);
		}
	}

	/**
	 * Updates the notification with given timestamp based on NotificationType.
	 * 
	 * @param notificationToUpdate
	 * @param notificationData
	 * @throws BusinessException
	 */
	private Notification updateNotification(Notification notificationToUpdate,
			WriteRequestVO notificationData) throws BusinessException {
		logger.debug("+ updateNotification()");

		if (notificationData.getNotificationType() == null) {
			throw new BusinessException("Invalid Notification Type : "
					+ notificationData.getNotificationType());
		}

		switch (NOTIFICATION_TYPE.valueOf(notificationData.getNotificationType())) {
		case REMINDER:
			notificationToUpdate.setReminder(notificationData.getTimestamp());
			break;
		case MESSAGE:
			notificationToUpdate.setMessage(notificationData.getTimestamp());
			break;
		default:
			throw new BusinessException("Invalid Notification Type : "
					+ notificationData.getNotificationType());
		}

		logger.debug("- updateNotification()");
		return notificationToUpdate;
	}

}
