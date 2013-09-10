package com.nsn.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.nsn.shared.AuthData;

/**
 * @author manu.mehrotra
 */
public interface AuthenticationService extends Remote {

	/**
	 * @return
	 * @throws RemoteException
	 */
	String authenticate() throws RemoteException;

	/**
	 * @param data
	 * @return
	 * @throws RemoteException
	 */
	boolean authenticate(AuthData data) throws RemoteException;
}
