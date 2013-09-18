package org.hibernate.ce.auction.dao.hibernate;

import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.dao.BillingDetailsDAO;
import org.hibernate.*;
import org.jboss.seam.annotations.*;

import java.util.*;

import static org.jboss.seam.ScopeType.EVENT;

/**
 * Hibernate-specific implementation of the <tt>BillingDetailsDAO</tt>
 * non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
@Name("billingDetailsDAO")
@Scope(EVENT)
public class BillingDetailsDAOHibernate
        extends     GenericHibernateDAO<BillingDetails, Long>
        implements  BillingDetailsDAO {

    @In(value = "caveatemptorDatabase", create = true)
    public void setSession(Session s) {
        session = s;
    }

    public BillingDetailsDAOHibernate() {
        super(BillingDetails.class);
    }

    public BillingDetailsDAOHibernate(Session session) {
        super(BillingDetails.class, session);
    }

    @SuppressWarnings("unchecked")
    public List<BillingDetails> findConcrete(Class concreteClass) {
        return getSession().createCriteria(concreteClass).list();
    }
}
