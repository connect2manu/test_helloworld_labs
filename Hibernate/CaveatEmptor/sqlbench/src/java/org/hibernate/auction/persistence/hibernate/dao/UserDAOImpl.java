package org.hibernate.auction.persistence.hibernate.dao;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Example;
import org.hibernate.auction.exceptions.InfrastructureException;
import org.hibernate.auction.model.User;
import org.hibernate.auction.persistence.hibernate.util.HibernateUtil;
import org.hibernate.auction.persistence.hibernate.util.HibernateUtil;
import org.hibernate.auction.persistence.dao.UserDAO;

import java.util.Collection;
import java.util.HashSet;

/**
 * A typical DAO for users using Hibernate.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class UserDAOImpl implements UserDAO {

	public UserDAOImpl() {
		HibernateUtil.beginTransaction();
	}

	// ********************************************************** //

	public User getUserById(Long userId, boolean lock)
			throws InfrastructureException {

		Session session = HibernateUtil.getSession();
		User user = null;
		try {
			if (lock) {
				user = (User) session.load(User.class, userId, LockMode.UPGRADE);
			} else {
				user = (User) session.load(User.class, userId);
			}
		}  catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return user;
	}

	// ********************************************************** //

	public Collection findAll()
			throws InfrastructureException {

		Collection users;
		try {
			users = HibernateUtil.getSession().createCriteria(User.class).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return users;
	}

	// ********************************************************** //

	public Collection findByExample(User exampleUser)
			throws InfrastructureException {

		Collection users;
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(User.class);
			users = crit.add(Example.create(exampleUser)).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return users;
	}

    // ********************************************************** //

    public Collection findUsersWithSelfBid()
            throws InfrastructureException {

        Collection users;
        try {
            String query = "select i.seller from Item i join i.bids b where i.seller = b.bidder";
            users = HibernateUtil.getSession().createQuery(query).list();
        } catch (HibernateException ex) {
            throw new InfrastructureException(ex);
        }
        return new HashSet(users); // Make result distinct
    }


	// ********************************************************** //

	public void makePersistent(User user)
			throws InfrastructureException {

		try {
			HibernateUtil.getSession().saveOrUpdate(user);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	// ********************************************************** //

	public void makeTransient(User user)
			throws InfrastructureException {

		try {
			HibernateUtil.getSession().delete(user);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}


}
