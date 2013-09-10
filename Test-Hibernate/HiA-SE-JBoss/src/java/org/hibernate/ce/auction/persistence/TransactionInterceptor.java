package org.hibernate.ce.auction.persistence;

import org.jboss.aop.joinpoint.Invocation;
import org.hibernate.*;
import org.apache.commons.logging.*;

/**
 * Wraps a database transaction around a method call, with thread-bound session-per-request.
 * <p>
 * This filter should be used if your <tt>hibernate.current_session_context_class</tt>
 * configuration is set to <tt>thread</tt> and you are not using JTA/CMT. You can use
 * this filter for a thread-bound <tt>Session</tt>, either with resource-local transactions
 * (direct JDBC) or user-managed JTA transactions. Set your
 * <tt>hibernate.transaction.factory_class</tt> accordingly.
 * <p>
 * This is a more flexible alternative to a servlet filter, or most other kinds
 * of interceptors. You can apply it to anything you can define an AOP pointcut for,
 * which is, well, everything. Usually you would wrap this interceptor around a
 * service facade method that needs a persistence context, or, in other words, that
 * uses DAOs. Note that you will have to enhance your bytecode to apply this filter
 * to arbitrary pointcuts. See build.xml and META-INF/jboss-aop.xml.
 * <p>
 * You could rewrite this interceptor easily for a session-per-conversation strategy. In
 * its given form it is best used to wrap a service method in a typical
 * session-per-request application.
 * <p>
 * Note that you should not use this interceptor out-of-the-box with enabled optimistic
 * concurrency control. Apply your own compensation logic for failed conversations, this
 * is totally dependent on your applications design.
 *
 * @see org.hibernate.ce.auction.web.filter.HibernateThreadFilter
 * @see org.hibernate.ce.auction.web.filter.HibernateThreadExtendedFilter
 *
 * @author christian@hibernate.org
 */
public class TransactionInterceptor
    implements org.jboss.aop.advice.Interceptor {

    private static Log log = LogFactory.getLog(TransactionInterceptor.class);

    private static SessionFactory sf = HibernateUtil.getSessionFactory();

    public String getName() {
        return "TransactionInterceptor";
    }

    public Object invoke(Invocation invocation) throws Throwable {

        try {
            log.debug("Starting a database transaction");
            sf.getCurrentSession().beginTransaction();

            log.debug("Invoking the aspectized service method");
            Object result = invocation.invokeNext();

            // Commit and cleanup
            log.debug("Committing the database transaction");
            sf.getCurrentSession().getTransaction().commit();

            return result;

        } catch (StaleObjectStateException staleEx) {
            log.error("This interceptor does not implement optimistic concurrency control!");
            log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only
            try {
                log.warn("Trying to rollback database transaction after exception");
                sf.getCurrentSession().getTransaction().rollback();
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }
            // Let others handle it... maybe another interceptor for exceptions?
            throw ex;
        }
    }

}
