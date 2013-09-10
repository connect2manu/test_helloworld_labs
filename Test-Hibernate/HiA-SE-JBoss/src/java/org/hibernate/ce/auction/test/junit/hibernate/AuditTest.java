package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.*;
import org.hibernate.ce.auction.dao.*;
import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.persistence.HibernateUtil;
import org.hibernate.ce.auction.persistence.audit.*;

import java.math.BigDecimal;
import java.util.*;

public class AuditTest extends HibernateTest {

    // This test does its own transaction handling
    protected void runTest() throws Throwable {
        wrapInTransaction = false;
        super.runTest();
    }

	// ********************************************************** //

	public void testAuditLog() throws Exception {

	    // Assign (global) interceptor and restart SessionFactory
        AuditLogInterceptor interceptor = new AuditLogInterceptor();
        sessionFactory = HibernateUtil.registerInterceptorAndRebuild(interceptor);

        interceptor.setSession(sessionFactory.getCurrentSession());
        sessionFactory.getCurrentSession().beginTransaction();

		// Save a user without audit logging (User class is not Auditable...)
		UserDAO userDAO = DAOFACTORY.getUserDAO();
		User u1 = new User("Christian", "Bauer", "turin", "abc123", "christian@hibernate.org");
		userDAO.makePersistent(u1);

		interceptor.setUserId(u1.getId());

		// Save an item with audit logging enabled
        Calendar inThreeDays = GregorianCalendar.getInstance();
        inThreeDays.roll(Calendar.DAY_OF_YEAR, 3);
		Item item = new Item("ONE", "Foo",
		        u1,
		        new MonetaryAmount(new BigDecimal("1.99"), Currency.getInstance(Locale.US)),
		        new MonetaryAmount(new BigDecimal("50.33"), Currency.getInstance(Locale.US)),
		        new Date(), inThreeDays.getTime());
		ItemDAO itemDAO = DAOFACTORY.getItemDAO();
		itemDAO.makePersistent(item);

		// Synchronize state to trigger interceptor
        sessionFactory.getCurrentSession().flush();

		// Check audit log
		Query queryAuditOne = sessionFactory.getCurrentSession()
                .createQuery("from AuditLogRecord lr where lr.entityId = :id");
		queryAuditOne.setParameter("id", item.getId());
		AuditLogRecord logRecordOne = (AuditLogRecord)queryAuditOne.uniqueResult();
		assertEquals(logRecordOne.userId, u1.getId());

        // Cleanup
        sessionFactory.getCurrentSession().getTransaction().rollback();

        // Unregister interceptor and rebuild
        HibernateUtil.resetInterceptor();
        HibernateUtil.rebuildSessionFactory();
	}

	// ********************************************************** //


}
