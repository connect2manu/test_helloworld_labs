package org.hibernate.ce.auction.web.filter;

import org.apache.commons.logging.*;
import org.hibernate.*;
import org.hibernate.transaction.*;
import org.hibernate.util.NamingHelper;
import org.hibernate.ce.auction.persistence.HibernateUtil;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.transaction.*;
import javax.naming.*;
import java.io.IOException;
import java.util.*;

/**
 * A servlet filter that provides a JTA transaction-bound session-per-request.
 * <p>
 * Use this filter for the <b>session-per-request</b> and the
 * <b>session-per-request-with-detached-objects</b>. This filter commits any
 * pending JTA  transaction when all page rendering and servlets have completed
 * executing. The Hibernate <tt>Session</tt> is flushed automatically on commit.
 * <p>
 * First note that this filter uses HibernateUtil/Hibernate to get the JTA
 * <tt>UserTransaction</tt> object. Hence, HibernateUtil will startup Hibernate
 * if first called. If you have a different method of deployment (JMX, for example),
 * rewrite the <tt>init()</tt> method of this filter to obtain the
 * <tt>UserTransaction</tt> in some other way.
 * <p>
 * This filter should be used if your <tt>hibernate.current_session_context_class</tt>
 * configuration is set to <tt>jta</tt> (or default if <tt>JTATransactionFactory</tt> is
 * enabled), and without CMT. It is a replacement for BMT in your EJB
 * session beans, encapsulating transaction demarcation in a single location.
 * An EJB/CMT environment does not require this filter, transaction
 * boundaries are set declaratively on EJB methods, not programmatically.
 * <p>
 * Note that you should not use this interceptor out-of-the-box with enabled optimistic
 * concurrency control. Apply your own compensation logic for failed conversations, this
 * is totally dependent on your applications design.
 *
 * @author christian@hibernate.org
 */
public class HibernateJTAFilter implements Filter {

    private static Log log = LogFactory.getLog(HibernateJTAFilter.class);

    private UserTransaction tx;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {
            log.debug("Starting a JTA transaction");
            tx.begin();

            // Call the next filter (continue request processing)
            chain.doFilter(request, response);

            // Commit and cleanup
            log.debug("Committing the JTA transaction");
            tx.commit();

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
                if (tx.getStatus() == Status.STATUS_ACTIVE) {
                    log.debug("Trying to rollback JTA transaction after exception");
                    tx.rollback();
                }
            } catch (Throwable rbEx) {
                log.error("Could not rollback JTA transaction after exception!", rbEx);
            }

            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        try {
            // Get a JNDI context for lookup, re-use Hibernate configuration
            Properties hibernateCfg =
                    HibernateUtil.getConfiguration().getProperties();
            Context jndiContext =
                    NamingHelper.getInitialContext( NamingHelper.getJndiProperties(hibernateCfg));

            // Find out the name of the JTA UserTransaction in JNDI, re-use Hibernate cfg
            TransactionManagerLookup tmLookup =
                    TransactionManagerLookupFactory.getTransactionManagerLookup(hibernateCfg);
            String userTxName = tmLookup.getUserTransactionName();

            log.debug("Initializing filter, obtaining JTA UserTransaction");
            tx = (UserTransaction) jndiContext.lookup(userTxName);

        } catch (NamingException ex) {
            throw new ServletException("Couldn't obtain JTA UserTransaction from JNDI" + ex);
        }
    }

    public void destroy() {}

}