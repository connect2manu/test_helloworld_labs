package com.manu.demo.application.al;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.util.HibernateUtil;
import com.manu.demo.xml.NotificationServiceResponse;

/**
 * Initializes the notification server related services and maintains the life cycle
 * of services.
 * 
 * @author manu.mehrotra
 */
public class AppStartupService {

	/**
	 * Logger instance for writing logs from this class.
	 */
	private final static Logger logger = LoggerFactory.getLogger(AppStartupService.class);

	/**
	 * Singleton instance of TQSService.
	 */
	private static AppStartupService tqsService = new AppStartupService();

	/**
	 * Holds the notification server config file properties.
	 */
	private PropertiesConfiguration config = null;
	
	/**
	 * Single re-usable JAXB Context instance used for converting
	 * java objects content trees back into XML format. 
	 */
	private JAXBContext jaxbConext = null;

	/**
	 * @return the config
	 */
	public PropertiesConfiguration getConfig() {
		return config;
	}

	/**
	 * Expiry period (in hours) to filter the notifications older than this period.
	 */
	private int NOTIFICATION_EXPIRY_PERIOD = 30;

	/**
	 * 
	 */
	private int CONFIG_REFRESH_DELAY_SECS = 30;
	
	/**
	 * Default private constructor.
	 */
	private AppStartupService() {
		logger.debug("+ inside TQSService()");
		initProperties();
	}

	/**
	 * Returns the singleton instance of TQSService.
	 * 
	 * @return
	 */
	public static AppStartupService getInstance() {
		logger.debug("TQSService | getInstance() = "+tqsService);
		return tqsService;
	}

	/**
	 * Initializes the external properties from 'notificationserver.conf' file.
	 */
	private void initProperties() {
		logger.debug("+ initProperties()");
		try {
			String path = System.getProperty("jboss.server.config.dir") + "/notificationservice.conf";
			logger.info("Loading TQS configuration from location = " + path);
			loadConfig(path);
			NOTIFICATION_EXPIRY_PERIOD = config.getInt("TRIGGER_EXPIRY_PERIOD_HOURS");
			logger.info("Configured Notification Expiry Period (in hours) = " + NOTIFICATION_EXPIRY_PERIOD);
		} catch (Exception exc) {
			if (logger.isTraceEnabled()) {
				exc.printStackTrace();
			}
			logger.error("Error occured while loading configuration. " + exc.getMessage()
					+ ". Now falling back to default configuration values.");
		}
		logger.debug("- initProperties()");
	}

	private void loadConfig(String path) throws ConfigurationException {
		logger.debug("Loading configuration... - " + path);
		
		config = new PropertiesConfiguration(path);
		FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
		CONFIG_REFRESH_DELAY_SECS = config.getInt("CONFIG_REFRESH_DELAY_SECS");
		logger.info("Configured auto refresh delay (in seconds) = " + CONFIG_REFRESH_DELAY_SECS);
		strategy.setRefreshDelay(CONFIG_REFRESH_DELAY_SECS * 1000);
		config.setReloadingStrategy(strategy);
		
		logger.debug("Notification Server configuration initialized.");
	}

	/**
	 * Returns the configured notifications expiry period (in hours).
	 * 
	 * @return
	 */
	public int getNotificationExpiryHours() {
		logger.debug("+ inside TQSService | getNotificationExpiryHours() > config = " + getConfig());
		try {
			NOTIFICATION_EXPIRY_PERIOD = getConfig().getInt("TRIGGER_EXPIRY_PERIOD_HOURS");
		} catch (Exception ex) {
			logger.error("Notification Server configuration property 'TRIGGER_EXPIRY_PERIOD_HOURS' is not set properly. Falling back to its former/default value.");
		}
		logger.info("TRIGGER EXPIRY PERIOD (Hours) = " + NOTIFICATION_EXPIRY_PERIOD);
		return NOTIFICATION_EXPIRY_PERIOD;
	}

	/**
	 * Stops notification server services. Cleans up the hibernate session factory
	 * and releases all in memory resources.
	 */
	public void cleanup() {
		logger.debug("+ cleanup()");
		HibernateUtil.cleanup();
		logger.debug("- cleanup()");
	}

	/**
	 * Returns JAXB Context.
	 * 
	 * @return
	 * @throws JAXBException
	 */
	public JAXBContext getJaxbContext() throws JAXBException {
		if(jaxbConext == null) {
			jaxbConext = JAXBContext.newInstance(NotificationServiceResponse.class);
		}
		return jaxbConext;
	}

}
