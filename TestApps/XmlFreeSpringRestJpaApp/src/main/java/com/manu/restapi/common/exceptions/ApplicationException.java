package com.manu.restapi.common.exceptions;

/**
 * @author manu.mehrotra
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer errorCode;
	private String errorMessage;
	private Throwable throwable;

	/**
	 * @param message
	 */
	public ApplicationException(Integer errorCode) {
		super();
		this.errorCode = errorCode;
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ApplicationException(Integer errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 * @param throwable
	 */
	public ApplicationException(Integer errorCode, String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.throwable = throwable;
	}

	/**
	 * @return String
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return Throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @param throwable
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}

