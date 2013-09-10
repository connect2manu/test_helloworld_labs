package org.hibernate.ce.auction.web.listener;

import org.hibernate.ce.auction.persistence.HibernateUtil;
import org.apache.commons.logging.*;

import javax.servlet.*;

/**
 * Start and shutdown Hibernate on context start and shutdown.
 * <p>
 * This class will call <tt>HibernateUtil.shutdown()</tt> to release all
 * resources held by Hibernate. Note that most servlet containers have
 * problems reloading contexts, and releasing the WebappClassloader. For
 * this reason you might still see OutOfMemoryException's when reloading
 * a context several times. As long as the WebappClassloader is not released
 * by the container (Hibernate does not hold a reference to it), all static
 * variables (including many in Hibernate) in the scope of this classloader
 * can not be garbage collected.
 *
 * @author christian.bauer@jboss.com
 *
 */
public class HibernateListener implements ServletContextListener {

    private static Log log = LogFactory.getLog(HibernateListener.class);

    public void contextInitialized(ServletContextEvent event) {
        log.info("Starting Hibernate persistence service...");
        HibernateUtil.getSessionFactory(); // Just call static initializer
        log.info("Hibernate startup complete");
    }

    public void contextDestroyed(ServletContextEvent event) {
        log.info("Shutting down Hibernate persistence service...");
        HibernateUtil.shutdown();
        log.info("Hibernate shutdown complete");
    }
}
