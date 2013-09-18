package org.hibernate.ce.auction.dao.ejb3;

import org.hibernate.ce.auction.dao.*;

/**
 * Returns EJB3-specific instances of DAOs.
 * <p>
 * TODO: This is untested and in a real EJB 3.0 application doesn't need a factory.
 *
 * @author christian.bauer@jboss.com
 */
public class Ejb3DAOFactory extends org.hibernate.ce.auction.dao.DAOFactory {

    public ItemDAO getItemDAO() {
        return new ItemDAOBean();
    }

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOBean();
    }

    public CommentDAO getCommentDAO() {
        return new CommentDAOBean();
    }

    public UserDAO getUserDAO() {
        return new UserDAOBean();
    }

    public CategorizedItemDAO getCategorizedItemDAO() {
        // TODO: Implement this
        return null;
    }

    public BillingDetailsDAO getBillingDetailsDAO() {
        // TODO: Implement this
        return null;
    }

    public ShipmentDAO getShipmentDAO() {
        // TODO: Implement this
        return null;
    }
}
