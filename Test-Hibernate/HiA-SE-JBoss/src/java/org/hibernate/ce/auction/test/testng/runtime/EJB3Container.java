package org.hibernate.ce.auction.test.testng.runtime;

import org.jboss.ejb3.embedded.*;
import org.testng.annotations.Configuration;

import javax.naming.*;

/**
 * Boots the JBoss Microcontainer with an EJB3 configuration.
 * <p>
 * You can also use this class to lookup beans from JNDI.
 *
 * @author christian.bauer@jboss.com
 */
public class EJB3Container {

    private static InitialContext initialContext;
    private EJB3StandaloneDeployer deployer;

    @Configuration(beforeSuite = true)
    public void startup() {
        try {

            // Boot the JBoss Microcontainer with EJB3 settings, loads ejb3-interceptors-aop.xml
            EJB3StandaloneBootstrap.boot(null);

            // Deploy CaveatEmptor beans (datasource, mostly)
            EJB3StandaloneBootstrap.deployXmlResource("caveatemptor-beans.xml");

            // Add all EJBs found in the archive that has this file
            deployer = new EJB3StandaloneDeployer();
            deployer.getArchivesByResource().add("META-INF/persistence.xml");

            // Deploy everything we got
            deployer.create();
            deployer.start();

            // Create InitialContext from jndi.properties
            initialContext = new InitialContext();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Configuration(afterSuite = true)
    public void shutdown() {
        try {
            deployer.stop();
            deployer.destroy();
            EJB3StandaloneBootstrap.shutdown();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Object lookup(String beanName) {
        try {
            return initialContext.lookup(beanName);
        } catch (NamingException ex) {
            throw new RuntimeException("Couldn't lookup: " + beanName, ex);
        }
    }

}
