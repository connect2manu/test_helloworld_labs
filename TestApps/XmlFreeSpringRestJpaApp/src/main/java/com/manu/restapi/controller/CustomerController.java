package com.manu.restapi.controller;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.manu.restapi.common.exceptions.ApplicationException;
import com.manu.restapi.common.exceptions.ResourceNotFoundException;
import com.manu.restapi.common.vo.CustomerCreateResponse;
import com.manu.restapi.common.vo.CustomerData;
import com.manu.restapi.common.vo.CustomerDeleteResponse;
import com.manu.restapi.common.vo.CustomerErrorResponse;
import com.manu.restapi.common.vo.CustomerGetResponse;
import com.manu.restapi.common.vo.CustomerUpdateRequest;
import com.manu.restapi.common.vo.CustomerUpdateResponse;
import com.manu.restapi.common.vo.TStatus;
import com.manu.restapi.services.CustomerService;

/**
 * Handles the requests received at context uri - "\customer".
 * 
 * @author manu.mehrotra
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	/**
	 * @param accountNumber
	 * @return
	 */
	@RequestMapping(value = "/{acctNum}", 
					method = RequestMethod.GET, 
					produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CustomerGetResponse get(@PathVariable("acctNum") Long accountNumber) {
		CustomerGetResponse customerGetResponse = new CustomerGetResponse();
		CustomerData customer = customerService.getCustomer(accountNumber);
		customerGetResponse.setCustomer(customer);
		return customerGetResponse;
	}
	
	/**
	 * @param accountNumber
	 */
	@RequestMapping(value = "/{acctNum}", 
					method = RequestMethod.POST, 
				 // consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
					produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CustomerCreateResponse create(@PathVariable("acctNum") Long accountNumber) {
		boolean success = customerService.createCustomer(accountNumber);

		CustomerCreateResponse objCreateResponse = new CustomerCreateResponse();
		if (success) {
			objCreateResponse.setStatus(TStatus.SUCCESS);
		}

		return objCreateResponse;
	}
	
	/**
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/{customerId}",
					method = RequestMethod.PUT, 
					produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CustomerUpdateResponse update(@PathVariable("customerId") int customerId,
			@RequestBody CustomerUpdateRequest objUpdateRequest) throws ApplicationException {

		if (objUpdateRequest == null) {
			throw new ApplicationException(1003,
					"Incorrect/Malformed CustomerUpdateRequest data provided in PUT request.");
		}

		CustomerUpdateResponse objUpdateResponse = new CustomerUpdateResponse();
		CustomerData customer = customerService.updateCustomer(customerId, objUpdateRequest.getCustomerData());
		objUpdateResponse.setCustomerData(customer);
		return objUpdateResponse;
	}
	
	/**
	 * @param accountNumber
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/{acctNum}", 
					method = RequestMethod.DELETE, 
					produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CustomerDeleteResponse delete(@PathVariable("acctNum") Long accountNumber) throws ApplicationException {
		CustomerDeleteResponse customerDeleteResponse = new CustomerDeleteResponse();
		boolean success = customerService.deleteCustomer(accountNumber);
		if (success == true) {
			customerDeleteResponse.setStatus(TStatus.SUCCESS);
		} else {
			throw new ApplicationException(1001, "Unable to delete customer of subscriber " + accountNumber);
		}
		return customerDeleteResponse;
	}

	@ExceptionHandler({ ApplicationException.class })
	@ResponseBody
	public CustomerErrorResponse handleApplicationException(ApplicationException appEx, HttpServletResponse response) {
		logger.error("CustomerController | handleApplicationException() > ErrorCode: " + appEx.getErrorCode()
				+ ", ErrorMessage: " + appEx.getErrorMessage());
		CustomerErrorResponse objErrorResponse = new CustomerErrorResponse();
		objErrorResponse.setErrorCode(appEx.getErrorCode());
		objErrorResponse.setErrorDescription(appEx.getErrorMessage());
		return objErrorResponse;
	}

	@ExceptionHandler({ Exception.class, RuntimeException.class })
	@ResponseBody
	public CustomerErrorResponse handleException(Exception ex, HttpServletResponse response) {
		logger.error("CustomerController | handleException() > Exception : " + ex);
		CustomerErrorResponse objErrorResponse = new CustomerErrorResponse();
		objErrorResponse.setErrorCode(1001);
		objErrorResponse.setErrorDescription("Internal System Error.");
		return objErrorResponse;
	}

}