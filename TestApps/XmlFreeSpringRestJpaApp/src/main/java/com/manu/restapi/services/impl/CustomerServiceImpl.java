package com.manu.restapi.services.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manu.restapi.common.exceptions.ResourceNotFoundException;
import com.manu.restapi.common.vo.CustomerData;
import com.manu.restapi.controller.HomeController;
import com.manu.restapi.dal.dao.CustomerDao;
import com.manu.restapi.dal.models.Customer;
import com.manu.restapi.services.CustomerService;

/**
 * @author manu.mehrotra
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Override
	public CustomerData getCustomer(Long accountNumber) {
		logger.info("CustomerServiceImpl | getCustomer() > Subs accountNumber = " + accountNumber);

		Customer customer = customerDao.getCustomerByAccountNumber(accountNumber);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer with AccountNumber (" + accountNumber + ") does not exist.");
		}

		CustomerData customerData = new CustomerData();
		customerData.setCustomerId(customer.getId());
		customerData.setName(customer.getName());
		customerData.setEmail(customer.getEmail());
		customerData.setImageloc(customer.getImageloc());
		return customerData;
	}

	@Override
	public boolean createCustomer(Long accountNumber) {
		logger.info("CustomerServiceImpl | createCustomer() > Subscriber accountNumber = " + accountNumber);
		return customerDao.createCustomer(getDefaultCustomer(accountNumber));
	}

	@Override
	public CustomerData updateCustomer(int customerId, CustomerData customerData) {
		logger.info("CustomerServiceImpl | updateCustomer() > customerId = " + customerId);

		Customer customer = customerDao.getCustomerByPK(customerId);
		customer.setEmail(customerData.getEmail());
		customer.setImageloc(customerData.getImageloc());
		customer.setName(customerData.getName());

		Customer outCustomer = customerDao.updateCustomer(customer);

		CustomerData customerDataResponse = new CustomerData();
		customerDataResponse.setCustomerId(outCustomer.getId());
		customerDataResponse.setName(outCustomer.getName());
		customerDataResponse.setEmail(outCustomer.getEmail());
		customerDataResponse.setImageloc(outCustomer.getImageloc());
		return customerDataResponse;
	}

	@Override
	public boolean deleteCustomer(Long accountNumber) {
		logger.info("CustomerServiceImpl | deleteCustomer() > Subscriber accountNumber = " + accountNumber);
		customerDao.deleteCustomer(accountNumber);
		return true;
	}

	/**
	 * @param accountNumber
	 * @return
	 */
	private Customer getDefaultCustomer(Long accountNumber) {
		Customer customer = new Customer();
		customer.setName(getDefaultCustomerTitle(accountNumber));
		customer.setAccountnumber(accountNumber.intValue());
		customer.setEmail("manu.mehrotra@live.com");
		customer.setImageloc(getDefaultImage());
		customer.setLastupdated(new Date(System.currentTimeMillis()));
		return customer;
	}

	/**
	 * @param accountNumber
	 * @return
	 */
	private String getDefaultCustomerTitle(Long accountNumber) {
		return "Customer_" + accountNumber;
	}

	/**
	 * TODO: Should be configurable.
	 * 
	 * @return
	 */
	private String getDefaultImage() {
		return "/defalt/poster/file/loc/thumbnail.gif";
	}
}
