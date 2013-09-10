package org.hibernate.auction.persistence.dao;

import org.hibernate.auction.model.User;
import org.hibernate.auction.exceptions.InfrastructureException;

import java.util.Collection;

public interface UserDAO {
    User getUserById(Long userId, boolean lock)
			throws InfrastructureException;

    Collection findAll()
			throws InfrastructureException;

    Collection findByExample(User exampleUser)
			throws InfrastructureException;

    Collection findUsersWithSelfBid()
            throws InfrastructureException;

    void makePersistent(User user)
			throws InfrastructureException;

    void makeTransient(User user)
			throws InfrastructureException;
}
