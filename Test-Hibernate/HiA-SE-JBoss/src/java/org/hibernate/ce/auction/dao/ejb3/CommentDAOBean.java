package org.hibernate.ce.auction.dao.ejb3;

import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.dao.CommentDAO;

import javax.ejb.Stateless;

/**
 * EJB3-specific implementation of the <tt>CommentDAO</tt> non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
public class CommentDAOBean extends GenericEjb3DAO<Comment, Long> implements CommentDAO {

    public CommentDAOBean() {
        super(Comment.class);
    }
}
