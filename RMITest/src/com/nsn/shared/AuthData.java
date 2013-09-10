package com.nsn.shared;

import java.io.Serializable;


/**
 * @author manu.mehrotra
 */
public class AuthData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginId = null;
	private String passsword = null;

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the passsword
	 */
	public String getPasssword() {
		return passsword;
	}

	/**
	 * @param passsword
	 *            the passsword to set
	 */
	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

}
