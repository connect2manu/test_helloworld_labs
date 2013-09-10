package org.hibernate.ce.auction.dao;

import org.hibernate.ce.auction.model.*;

import java.util.*;

/**
 * Business DAO operations related to the <tt>BillingDetails</tt> entity.
 * <p>
 * @see org.hibernate.ce.auction.model.BillingDetails
 * @see org.hibernate.ce.auction.model.CreditCard
 * @see org.hibernate.ce.auction.model.BankAccount
 *
 * @author christian.bauer@jboss.com
 */
public interface BillingDetailsDAO extends GenericDAO<BillingDetails, Long> {

    public List<BillingDetails> findConcrete(Class concreteClass);

}
