package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.persistence.*;
import org.hibernate.ce.auction.dao.DAOFactory;
import org.hibernate.*;
import org.apache.commons.logging.*;

public abstract class HibernateTest extends junit.framework.TestCase {

    private static Log log = LogFactory.getLog(HibernateTest.class);

    protected final DAOFactory DAOFACTORY = DAOFactory.HIBERNATE;
    protected SessionFactory sessionFactory;

    protected boolean wrapInTransaction = true;
    protected boolean rollback = true;

    protected void runTest() throws Throwable {
        // Get the current SessionFactory
        sessionFactory = HibernateUtil.getSessionFactory();

        if (wrapInTransaction) {
            log.debug("Wrapping test in a transaction");
            try {
                log.debug("Begin transaction");
                sessionFactory.getCurrentSession().beginTransaction();
                log.debug("Executing inTransaction() supplement");
                inTransaction();
                log.debug("Running test");
                super.runTest();
            } finally {
                if (!rollback) {
                    log.debug("Committing transaction");
                    sessionFactory.getCurrentSession().getTransaction().commit();
                } else {
                    log.debug("Rolling back transaction");
                    sessionFactory.getCurrentSession().getTransaction().rollback();
                }
            }
        } else {
            super.runTest();
        }
    }

    /**
     * Executes inside the transaction.
     * <p>
     * Override this method to execute extra operations, such as test data import.
     */
    public void inTransaction() {}

}
