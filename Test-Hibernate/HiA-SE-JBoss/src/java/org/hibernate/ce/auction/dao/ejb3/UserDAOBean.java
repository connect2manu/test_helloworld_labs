package org.hibernate.ce.auction.dao.ejb3;

import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.dao.UserDAO;

import javax.ejb.Stateless;

/**
 * EJB3-specific implementation of the <tt>UserDAO</tt> non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
public class UserDAOBean extends GenericEjb3DAO<User, Long> implements UserDAO {

    public UserDAOBean() {
        super(User.class);
    }

    public User validateLogin(User user) {
        return null;  // TODO: Implement this
    }

    // Could be in a separate interface..
    public void persistAddress(AddressEntity address) {
        // TODO: Implement this
    }
}
