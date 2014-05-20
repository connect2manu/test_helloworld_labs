package com.manu.restapi.dal.dao;

import org.springframework.stereotype.Repository;

import com.manu.restapi.dal.models.Customer;

/**
 * @author manu.mehrotra
 */
@Repository
public interface CustomerDao {

	public Customer getCustomerByPK(int customerId);

	public Customer getCustomerByAccountNumber(Long accountNumber);

	public boolean createCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public boolean deleteCustomer(Long accountNumber);

}
