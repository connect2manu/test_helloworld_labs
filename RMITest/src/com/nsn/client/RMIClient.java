package com.nsn.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.nsn.server.service.AuthenticationService;
import com.nsn.shared.AuthData;
import com.nsn.shared.ServiceRegistry;

/**
 * @author manu.mehrotra
 */
public class RMIClient {

	public RMIClient() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = args.length < 1 ? null : args[0];
		try {
			// If host not provided or null, then localhost is used with default
			// RMI port 1099. Else provide the host where RMI Registry is
			// running which contains the already binded remote stubs.
			Registry registry = LocateRegistry.getRegistry(host);
			AuthenticationService remoteStub = (AuthenticationService) registry.lookup(ServiceRegistry.AUTH_SERVICE);

			String response = remoteStub.authenticate();
			System.out.println("response1: " + response);

			AuthData data = new AuthData();
			data.setLoginId("user1");
			data.setPasssword("pass1");

			boolean success = remoteStub.authenticate(data);
			System.out.println("response2: " + success);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
