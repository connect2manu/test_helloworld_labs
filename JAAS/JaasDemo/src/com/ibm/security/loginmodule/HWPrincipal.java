/*
 * ===========================================================================
 * Licensed Materials - Property of IBM
 * 
 * (C) Copyright IBM Corp. 2000 All Rights Reserved.
 * 
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 * ===========================================================================
 * 
 * File: HWPrincipal.java
 */

package com.ibm.security.loginmodule;

import java.security.Principal;

/**
 * This class implements the Principal interface and represents a HelloWorld
 * tester.
 * 
 * @version 1.1, 09/10/99
 * @author D. Kent Soper
 */
public class HWPrincipal implements Principal, java.io.Serializable {

	private final String name;

	/*
	 * Create a HWPrincipal with the supplied name.
	 */
	public HWPrincipal(String name) {
		if (name == null) {
			throw new NullPointerException("illegal null input");
		}

		this.name = name;
	}

	/*
	 * Return the name for the HWPrincipal.
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * Return a string representation of the HWPrincipal.
	 */
	@Override
	public String toString() {
		return "HWPrincipal:  " + name;
	}

	/*
	 * Compares the specified Object with the HWPrincipal for equality.
	 * Returns true if the given object is also a HWPrincipal and the
	 * two HWPrincipals have the same user name.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof HWPrincipal)) {
			return false;
		}
		HWPrincipal that = (HWPrincipal) o;

		if (this.getName().equals(that.getName())) {
			return true;
		}
		return false;
	}

	/*
	 * Return a hash code for the HWPrincipal.
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
