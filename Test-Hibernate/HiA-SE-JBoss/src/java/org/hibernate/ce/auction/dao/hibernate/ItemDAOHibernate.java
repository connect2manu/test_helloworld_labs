package org.hibernate.ce.auction.dao.hibernate;

import static org.jboss.seam.ScopeType.EVENT;
import org.jboss.seam.annotations.*;
import org.hibernate.ce.auction.dao.ItemDAO;
import org.hibernate.ce.auction.model.*;
import org.hibernate.*;

/**
 * Hibernate-specific implementation of the <tt>ItemDAO</tt> non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
@Name("itemDAO")
@Scope(EVENT)
public class ItemDAOHibernate
        extends     GenericHibernateDAO<Item, Long>
        implements  ItemDAO {

    @In(value = "caveatemptorDatabase", create = true)
    public void setSession(Session s) {
        session = s;
    }

    public ItemDAOHibernate() {
        super(Item.class);
    }

    public ItemDAOHibernate(Session session) {
        super(Item.class, session);
    }

    public Bid getMaxBid(Long itemId) {
        Query q = getSession().getNamedQuery(ItemDAO.QUERY_MAXBID);
        q.setParameter("itemid", itemId);
        return (Bid) q.uniqueResult();
    }

    public Bid getMinBid(Long itemId) {
        Query q = getSession().getNamedQuery(ItemDAO.QUERY_MINBID);
        q.setParameter("itemid", itemId);
        return (Bid) q.uniqueResult();
    }

}
