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
 * File: HelloWorld.java
 */
package com.ibm.security;

import java.io.File;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * This SampleLogin application attempts to authenticate a user. If the user
 * successfully authenticates itself, the user name and number of Credentials is
 * displayed.
 * 
 * @version 1.1, 09/14/99
 */
public class HelloWorld {

	/**
	 * Attempt to authenticate the user.
	 */
	public static void main(String[] args) {
		// use the configured LoginModules for the "helloWorld" entry
		LoginContext lc = null;
		try {
			lc = new LoginContext("helloWorld", new MyCallbackHandler());
		} catch (LoginException le) {
			le.printStackTrace();
			System.exit(-1);
		}

		// the user has 3 attempts to authenticate successfully
		int i;
		for (i = 0; i < 3; i++) {
			try {

				// attempt authentication
				lc.login();

				// if we return with no exception, authentication succeeded
				break;

			} catch (AccountExpiredException aee) {

				System.out.println("Your account has expired");
				System.exit(-1);

			} catch (CredentialExpiredException cee) {

				System.out.println("Your credentials have expired.");
				System.exit(-1);

			} catch (FailedLoginException fle) {

				System.out.println("Authentication Failed");
				try {
					Thread.currentThread();
					Thread.sleep(3000);
				} catch (Exception e) {
					// ignore
				}

			} catch (Exception e) {

				System.out.println("Unexpected Exception - unable to continue");
				e.printStackTrace();
				System.exit(-1);
			}
		}

		// did they fail three times?
		if (i == 3) {
			System.out.println("Sorry");
			System.exit(-1);
		}

		// Look at what Principals we have:
		Iterator principalIterator = lc.getSubject().getPrincipals().iterator();
		System.out.println("\n\nAuthenticated user has the following Principals:");
		while (principalIterator.hasNext()) {
			Principal p = (Principal) principalIterator.next();
			System.out.println("\t" + p.toString());
		}

		// Look at some Principal-based work:
		Subject.doAsPrivileged(lc.getSubject(), new PrivilegedAction() {
			@Override
			public Object run() {
				System.out.println("\nYour java.home property: " + System.getProperty("java.home"));

				System.out.println("\nYour user.home property: " + System.getProperty("user.home"));

				File f = new File("foo.txt");
				System.out.print("\nfoo.txt does ");
				if (!f.exists()) {
					System.out.print("not ");
				}
				System.out.println("exist in your current directory");

				System.out.println("\nOh, by the way ...");

				try {
					Thread.currentThread();
					Thread.sleep(2000);
				} catch (Exception e) {
					// ignore
				}
				System.out.println("\n\nHello World!\n");
				return null;
			}
		}, null);
		System.exit(0);
	}
}