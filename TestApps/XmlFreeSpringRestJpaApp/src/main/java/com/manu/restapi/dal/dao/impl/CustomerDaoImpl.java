package com.manu.restapi.dal.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manu.restapi.common.exceptions.ResourceNotFoundException;
import com.manu.restapi.dal.dao.CustomerDao;
import com.manu.restapi.dal.models.Customer;


@Repository
@Transactional(value = "txManagerMySqlDB", propagation = Propagation.REQUIRED)
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext(name = "entityManagerFactoryBeanForMySql", unitName = "CustomerMySqlUnit")
	private EntityManager em;

	@Override
	@Transactional(value = "txManagerMySqlDB", propagation = Propagation.SUPPORTS)
	public Customer getCustomerByPK(int customerId) {
		return em.find(Customer.class, customerId);
	}

	@Override
	@Transactional(value = "txManagerMySqlDB", propagation = Propagation.SUPPORTS)
	public Customer getCustomerByAccountNumber(Long accountNumber) {
		// return em.find(Customer.class, accountNumber);

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);

		/*
		 * Swap criteria statements if you would like to try out type-safe criteria queries, a new
		 * feature in JPA 2.0 criteria.select(customer).orderBy(cb.asc(customer.get(Customer_.name)));
		 */
		criteria.select(customer).where(builder.equal(customer.get("accountnumber"), accountNumber));
		return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public boolean createCustomer(Customer customer) {
		em.persist(customer);
		return true;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return em.merge(customer);
	}

	@Override
	public boolean deleteCustomer(Long accountNumber) {
		Customer customer = getCustomerByAccountNumber(accountNumber);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer with AccountNumber (" + accountNumber + ") does not exist.");
		}
		em.remove(customer);
		return true;
	}

}

