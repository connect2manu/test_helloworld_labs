package com.manu.demo.application.al.exceptions;

/**
 * This exception is used to mark business rule violations.
 * 
 * @author manu.mehrotra
 */
public class BusinessException extends RuntimeException {

	/**
	 * Default serial verion uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public BusinessException() {
	}

	/**
	 * Constructor taking string error message.
	 * 
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * Constructor taking string error message and Throwable error object.
	 * 
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor taking Throwable error object.
	 * 
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}
}
