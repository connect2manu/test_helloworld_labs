package org.hibernate.auction.bench.server;

import net.sf.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.auction.persistence.hibernate.util.HibernateUtil;

/**
 * A Hibernate-specific implementation of the auction services.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class HibernateAuctionService extends GenericAuctionService {

    // Housekeeping
    public void setUp(Class daoFactoryImpl) throws Exception {
        super.setUp(daoFactoryImpl);
        SchemaExport ddlExport = new SchemaExport(HibernateUtil.getConfiguration());
        ddlExport.create(false, true);
    }

    public void tearDown() throws Exception {
        SchemaExport ddlExport = new SchemaExport(HibernateUtil.getConfiguration());
        ddlExport.drop(false, true);
        HibernateUtil.getSessionFactory().close();
    }

    // Transaction control
    public void beginTransaction() {
        // Implicit for Hibernate thread-local Session
    }

    public void endTransaction() {
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
    }

}
