package com.nsn.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.nsn.server.service.AuthenticationService;
import com.nsn.shared.AuthData;
import com.nsn.shared.ServiceRegistry;

/**
 * @author manu.mehrotra
 */
public class AuthServiceImpl /*extends UnicastRemoteObject*/implements AuthenticationService {

	protected AuthServiceImpl() /*throws RemoteException*/{
		/*super();*/
	}

	@Override
	public String authenticate() {
		return "Authentication Successfull !!!";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AuthServiceImpl serviceObj = new AuthServiceImpl();
			// 1099 is default port.
			AuthenticationService serviceStub = (AuthenticationService) UnicastRemoteObject.exportObject(serviceObj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind(ServiceRegistry.AUTH_SERVICE, serviceStub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public boolean authenticate(AuthData authData) throws RemoteException {
		if (authData != null) {
			if (authData.getLoginId().equals("user1") && authData.getPasssword().equals("pass1")) {
				return true;
			}
		}
		return false;
	}

}
