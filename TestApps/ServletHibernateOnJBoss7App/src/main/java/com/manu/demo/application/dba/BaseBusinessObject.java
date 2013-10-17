package com.manu.demo.application.dba;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.manu.demo.application.al.exceptions.InfrastructureException;
import com.manu.demo.application.util.HibernateUtil;

/**
 * Marker for business objects to extend.
 * 
 * @author manu.mehrotra
 */
public abstract class BaseBusinessObject {

	/**
	 * Default constructor.
	 */
	public BaseBusinessObject() {
	}

	/**
	 * @return
	 * @throws InfrastructureException
	 */
	public Session getSession() throws InfrastructureException {
		return HibernateUtil.getSession();
	}

	/**
	 * @throws InfrastructureException
	 */
	public void closeSession() throws InfrastructureException {
		HibernateUtil.closeSession();
	}

	/**
	 * @return
	 * @throws InfrastructureException
	 */
	public Transaction beginTransaction() throws InfrastructureException {
		return HibernateUtil.beginTransaction();
	}

	/**
	 * @param session
	 * @return
	 * @throws InfrastructureException
	 */
	public Transaction beginTransaction(Session session) throws InfrastructureException {
		return HibernateUtil.beginTransaction(session);
	}

	/**
	 * @throws InfrastructureException
	 */
	public void commit() throws InfrastructureException {
		HibernateUtil.commitTransaction();
	}

	/**
	 * @throws InfrastructureException
	 */
	public void rollback() throws InfrastructureException {
		HibernateUtil.rollbackTransaction();
	}

}
