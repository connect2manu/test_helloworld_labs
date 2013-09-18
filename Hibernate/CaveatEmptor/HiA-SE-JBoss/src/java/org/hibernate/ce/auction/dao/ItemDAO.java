package org.hibernate.ce.auction.dao;

import org.hibernate.ce.auction.model.*;

/**
 * Business DAO operations related to the <tt>Item</tt> entity.
 * <p>
 * Note the usage of the constants for named query declaration. That way,
 * naming of queries is centralized and checked at compile time. Of course,
 * this can be broken by externalizing queries into XML metadata, but it
 * is type-safe for queries declared in annotations. See the entity class
 * source for an example.
 *
 * @see Item
 *
 * @author christian.bauer@jboss.com
 */
public interface ItemDAO extends GenericDAO<Item, Long> {

    public static final String QUERY_MAXBID = "ItemDAO.QUERY_MAXBID";
    public static final String QUERY_MINBID = "ItemDAO.QUERY_MINBID";

    Bid getMaxBid(Long itemId);
    Bid getMinBid(Long itemId);

}
