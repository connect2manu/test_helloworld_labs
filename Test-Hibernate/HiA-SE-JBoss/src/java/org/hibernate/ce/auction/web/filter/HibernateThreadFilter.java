package org.hibernate.ce.auction.web.filter;

import org.apache.commons.logging.*;
import org.hibernate.*;
import org.hibernate.ce.auction.persistence.HibernateUtil;

import javax.servlet.*;
import javax.servlet.Filter;
import java.io.IOException;

/**
 * A servlet filter that provides a thread-bound session-per-request.
 * <p>
 * This filter should be used if your <tt>hibernate.current_session_context_class</tt>
 * configuration is set to <tt>thread</tt> and you are not using JTA/CMT. You can use
 * this filter for a thread-bound <tt>Session</tt>, either with resource-local transactions
 * (direct JDBC) or user-managed JTA transactions. Set your
 * <tt>hibernate.transaction.factory_class</tt> accordingly.
 * <p>
 * An alternative, more flexible solution is <tt>TransactionInterceptor</tt>
 * that can be applied to any pointcut with JBoss AOP.
 * <p>
 * Note that you should not use this interceptor out-of-the-box with enabled optimistic
 * concurrency control. Apply your own compensation logic for failed conversations, this
 * is totally dependent on your applications design.
 *
 * @see org.hibernate.ce.auction.persistence.TransactionInterceptor
 *
 * @author christian@hibernate.org
 */
public class HibernateThreadFilter implements Filter {

    private static Log log = LogFactory.getLog(HibernateThreadFilter.class);

    private SessionFactory sf;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {
            log.debug("Starting a database transaction");
            sf.getCurrentSession().beginTransaction();

            // Call the next filter (continue request processing)
            chain.doFilter(request, response);

            // Commit and cleanup
            log.debug("Committing the database transaction");
            sf.getCurrentSession().getTransaction().commit();

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
            ex.printStackTrace();
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    log.debug("Trying to rollback database transaction after exception");
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }

            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Initializing filter, obtaining Hibernate SessionFactory from HibernateUtil");
        sf = HibernateUtil.getSessionFactory();
    }

    public void destroy() {}

}