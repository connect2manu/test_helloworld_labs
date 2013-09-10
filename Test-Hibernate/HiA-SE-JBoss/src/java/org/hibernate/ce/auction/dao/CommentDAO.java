package org.hibernate.ce.auction.dao;

import org.hibernate.ce.auction.model.Comment;

/**
 * Business DAO operations related to the <tt>Comment</tt> entity.
 * <p>
 * This is an empty interface, currently there are no business-oriented
 * data access operations for this class. However, preparing the interface
 * means preparing ourself for future changes. That way, no refactoring is
 * necessary should we add methods one day. Note that with a sensible
 * factory, such as the <tt>HibernateDAOFactory</tt>, no concrete implementation
 * of this empty interface is needed, but a parameterized <tt>GenericDAO</tt>
 * for Hibernate can be constructed and used.
 *
 * @see Comment
 *
 * @author christian.bauer@jboss.com
 */
public interface CommentDAO extends GenericDAO<Comment, Long> {
    // Empty
}
