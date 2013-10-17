package com.manu.demo.application.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.exceptions.BusinessException;
import com.manu.demo.application.al.exceptions.InfrastructureException;
import com.manu.demo.application.dba.NotificationDAO;

/**
 * Processes the write notification request and checks if the record with given
 * MACAddress doesn't exists then inserts the new record else update the
 * existing one.
 * 
 * @author manu.mehrotra
 */
public class WriteNotificationHelper {

	private final Logger logger = LoggerFactory
			.getLogger(WriteNotificationHelper.class);
	
	private final static WriteNotificationHelper writeProcessor = new WriteNotificationHelper();

	/**
	 * Synchronizes write requests to handle the scenario of 
	 * multiple concurrent inserts for same MACAddress, which is a Primary Key.
	 * 
	 * @param notificationData
	 * @throws InfrastructureException
	 */
	public synchronized boolean process(WriteRequestVO notificationData)
			throws InfrastructureException, BusinessException {
		logger.debug("+ process() | RequestData:: \n " + notificationData.toString());

		NotificationDAO notificationDAO = new NotificationDAO();
		boolean isNew = notificationDAO.loadAndSaveNotification(notificationData);

		logger.debug("- process() | isInsert = "+isNew);
		return isNew;
	}

	public static WriteNotificationHelper getInstance() {
		return writeProcessor;
	}

}
