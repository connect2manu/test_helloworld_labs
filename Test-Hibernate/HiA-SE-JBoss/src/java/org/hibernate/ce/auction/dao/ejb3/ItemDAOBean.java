package org.hibernate.ce.auction.dao.ejb3;

import org.hibernate.ce.auction.dao.ItemDAO;
import org.hibernate.ce.auction.model.*;

import javax.ejb.Stateless;
import javax.persistence.*;

/**
 * EJB3-specific implementation of the <tt>ItemDAO</tt> non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
@Stateless
public class ItemDAOBean extends GenericEjb3DAO<Item, Long> implements ItemDAO {

    public ItemDAOBean() {
        super(Item.class);
    }

    public Bid getMaxBid(Long itemId) {
        Query q = em.createNamedQuery(ItemDAO.QUERY_MAXBID);
        q.setParameter("itemid", itemId);
        return (Bid) q.getSingleResult();
    }

    public Bid getMinBid(Long itemId) {
        Query q = em.createNamedQuery(ItemDAO.QUERY_MINBID);
        q.setParameter("itemid", itemId);
        return (Bid) q.getSingleResult();
    }

}
