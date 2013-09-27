package com.manu.restapi.services;

import org.springframework.stereotype.Service;

import com.manu.restapi.common.vo.CustomerData;

/**
 * @author manu.mehrotra
 */
@Service
public interface CustomerService {

	public CustomerData getCustomer(Long accountNumber);

	public boolean createCustomer(Long accountNumber);

	public CustomerData updateCustomer(int customerId, CustomerData customerData);

	public boolean deleteCustomer(Long accountNumber);
}
