package com.manu.demo.application.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.exceptions.InfrastructureException;

/**
 * Basic Hibernate helper class, handles SessionFactory, Session and
 * Transaction.
 * <p>
 * Handles initial SessionFactory creation and holds Session and Transactions in
 * thread local variables.
 * <p>
 * All exceptions are wrapped in an unchecked InfrastructureException.
 * 
 * @author manu.mehrotra
 */
public class HibernateUtil {

	private final static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	// private static Log log = LogFactory.getLog(HibernateUtil.class);

	private static Configuration configuration = null;
	private static SessionFactory sessionFactory = null;
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();

	/**
	 * Static block loads and configures the hibernate configuration and creates the
	 * hibernate session factory from the configuration properties.
	 */
	static {
		try {
			logger.info("+ Creating Hibernate Factory...");
			configuration = new Configuration().configure();
			configuration.setProperty("hibernate.listeners.envers.autoRegister", "false");
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			logger.info("- Hibernate factory successfully created.");
		} catch (Throwable ex) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			logger.error("Building SessionFactory failed. ", ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	/**
	 * Private constructor.
	 */
	private HibernateUtil() {
	}

	/**
	 * Returns the SessionFactory instance.
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		logger.debug("+ inside HibernateUtil | getSessionFactory()");
		return sessionFactory;
	}

	/**
	 * Returns the original Hibernate configuration.
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Retrieves the current Session local to the thread.
	 * <p/>
	 * If no Session is open, opens a new Session for the running thread.
	 * 
	 * @return Session
	 */
	public static Session getSession() throws InfrastructureException {
		logger.debug("+ getSession()");
		Session session = threadSession.get();
		try {
			if (session == null) {
				logger.debug("Opening new Session for this thread.");
				session = getSessionFactory().openSession();
				threadSession.set(session);
				logger.debug("session flush mode = " + session.getFlushMode());
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		logger.debug("- getSession() | session = " + session);
		return session;
	}

	/**
	 * Starts a new database transaction.
	 * 
	 * @return
	 */
	public static Transaction beginTransaction() throws InfrastructureException {
		return beginTransaction(getSession());
	}

	/**
	 * Starts a new database transaction.
	 * 
	 * @param session
	 * @return
	 */
	public static Transaction beginTransaction(Session session) throws InfrastructureException {
		Transaction tx = threadTransaction.get();
		try {
			if (session == null) {
				session = getSession();
			}
			if (tx == null) {
				logger.debug("Starting new database transaction in this thread.");
				tx = session.beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return tx;
	}

	/**
	 * Commit the database transaction, rollbacks the transaction in case of any
	 * issue and closes the session.
	 */
	public static void commitTransaction() throws InfrastructureException {
		logger.debug("+ commitTransaction()");
		Transaction tx = threadTransaction.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				logger.debug("Committing database transaction of this thread.");
				if(logger.isDebugEnabled()) {
					if(tx.isInitiator()) {
						logger.debug("Commiting Transaction. It is an initiator transaction. Session will be flushed before commit.");
					}
				}
				tx.commit();
			}
			threadTransaction.set(null);
		} catch (HibernateException ex) {
			rollbackTransaction();
			throw new InfrastructureException(ex);
		} finally {
			closeSession();
		}
		logger.debug("- commitTransaction()");
	}

	/**
	 * Rollbacks the database transaction.
	 */
	public static void rollbackTransaction() throws InfrastructureException {
		logger.debug("+ rollbackTransaction()");
		Transaction tx = threadTransaction.get();
		if (tx == null) {
			throw new IllegalStateException("Error getting Transaction handle.");
		}
		logger.debug(" Rolling back the transaction [" + tx.toString() + "]");
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				logger.debug("Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
			threadTransaction.set(null);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			closeSession();
		}
		logger.debug("- rollbackTransaction()");
	}

	/**
	 * Closes the Session local to the thread.
	 * 
	 * @throws InfrastructureException
	 */
	public static void closeSession() throws InfrastructureException {
		logger.debug("+ closeSession()");
		try {
			Session s = threadSession.get();
			if (s != null && s.isOpen()) {
				logger.debug("Closing Session of this thread.");
				s.close();
			}
			threadSession.set(null);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		logger.debug("- closeSession()");
	}

	/**
	 * Closes the current SessionFactory and releases all resources.
	 * <p>
	 * The only other method that can be called on HibernateUtil after this one
	 * is rebuildSessionFactory().
	 */
	public static void cleanup() throws InfrastructureException {
		logger.info("+ inside HibernateUtil | shutdown()");

		// Close any existing open session.
		Session session = threadSession.get();
		if (session != null) {
			session.close();
		}

		// Close caches and connection pools
		if (sessionFactory != null) {
			logger.debug("Closing hibernate session facotry.");
			sessionFactory.close();
		}

		logger.info("- inside HibernateUtil | shutdown()");
	}

}