package org.hibernate.ce.auction.dao;

import org.hibernate.ce.auction.model.*;

/**
 * Business DAO operations related to the <tt>User</tt> entity.
 *
 * @see User
 *
 * @author christian.bauer@jboss.com
 */
public interface UserDAO extends GenericDAO<User, Long> {

    public User validateLogin(User user);

    // Could be in a separate interface..
    public void persistAddress(AddressEntity address);
}
