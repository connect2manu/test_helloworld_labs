package com.manu.demo.application.al.exceptions;


/**
 * This exception is used to mark (fatal) failures in infrastructure and system
 * code.
 * 
 * @author manu.mehrotra
 */
public class InfrastructureException extends RuntimeException {

	/**
	 * Default serial verion uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public InfrastructureException() {
	}

	/**
	 * Constructor taking string error message.
	 * 
	 * @param message
	 */
	public InfrastructureException(String message) {
		super(message);
	}

	/**
	 * Constructor taking string error message and Throwable error object.
	 * 
	 * @param message
	 * @param cause
	 */
	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor taking Throwable error object.
	 * 
	 * @param cause
	 */
	public InfrastructureException(Throwable cause) {
		super(cause);
	}
}
