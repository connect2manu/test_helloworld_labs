package com.interceptor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import com.interceptor.plugin.Filterable;
import com.plugins.ipfilter.IPFilter;

/**
 * 
 */
class RequestInterceptor {

	private static Properties proxyProps = new Properties();
	private static String localPort = null;
	private static String remoteHost = null;
	private static String remotePort = null;

	public RequestInterceptor() {
		try {
			proxyProps.load(this.getClass().getClassLoader()
					.getResourceAsStream("com/interceptor/config/interceptor_config.properties"));
			localPort = proxyProps.getProperty("local.client.port");
			remoteHost = proxyProps.getProperty("remote.server.address");
			remotePort = proxyProps.getProperty("remote.server.port");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) {
		LoadProperties();

		int localport = -1;
		int remoteport = -1;
		String remotehost = null;

		Socket incomingClientSocket = null;
		Socket serverResponseSocket = null;
		ServerSocket clientDataSocket = null;

		try {
			localport = Integer.parseInt(localPort);
			remotehost = remoteHost;
			remoteport = Integer.parseInt(remotePort);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage() + "\n");
			shutdownProxy();
		}

		// Check for valid local and remote port, hostname not null
		if (!validateProps(localport, remoteport, remotehost)) {
			shutdownProxy();
		}

		try {
			/*
			 * Test and create a listening socket at proxy.
			 * Acts as proxy server for the Client and client will send data to this port.
			 */
			System.out.println("localport=" + localport);
			clientDataSocket = new ServerSocket(localport);
		} catch (IOException e) {
			System.err.println("Problem listening local port " + localport);
			e.printStackTrace();
			shutdownProxy();
		}

		try {
			/*
			 * Create the two threads for the incoming and outgoing traffic of proxy server. 
			 */
			System.out.println("remotehost=" + remotehost + ", remoteport = " + remoteport);
			serverResponseSocket = new Socket(remotehost, remoteport);
		} catch (IOException e) {
			System.err.println("Problem listening remote port:" + remoteport);
			e.printStackTrace();
			shutdownProxy();
		}

		/*
		 * Loop to listen for incoming client connection, and accept if there is one. 
		 */
		while (true) {
			try {
				System.out.println("Listening... client port: " + localport);
				incomingClientSocket = clientDataSocket.accept();

				/*
				 * Can load multiple filter's here through reflection (using URLClassLoader)
				 * and call their filter method to validate/filter the client requests.
				 */
				Filterable ipFilter = IPFilter.getInstance();
				if (!ipFilter.filter(incomingClientSocket)) {
					System.out.println("Blocked IP:" + incomingClientSocket);
					incomingClientSocket.close();
					continue;
				}

				WriteDataChannel fwdRequestToServer = new WriteDataChannel(incomingClientSocket, serverResponseSocket);
				fwdRequestToServer.run();
				System.out.println("Forward Request To Server - Done.");

				WriteDataChannel sendResponseToClient = new WriteDataChannel(serverResponseSocket, incomingClientSocket);
				sendResponseToClient.run();
				System.out.println("Send Response To Client - Done.");
			} catch (UnknownHostException e) {
				// Test and make connection to remote host
				System.err.println("Error: Unknown Host " + remotehost);
				shutdownProxy();
			} catch (IOException e) {
				System.err.println("Error: Couldn't Initiate I/O connection for " + remotehost);
				shutdownProxy();
			} finally {
				try {
					/*
					 * Close the serviced client socket and keep listening for new one.
					 */
					incomingClientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 */
	private static void LoadProperties() {
		RequestInterceptor interceptor = new RequestInterceptor();
	}

	/**
	 * @param localport
	 * @param remoteport
	 * @param remotehost
	 * @return
	 */
	private static boolean validateProps(int localport, int remoteport, String remotehost) {
		System.out.println("Checking: Port" + localport + " to " + remotehost + " Port " + remoteport);

		if (localport <= 0) {
			System.err.println("Error: Invalid Local Port Specification " + "\n");
			return false;
		}
		if (remoteport <= 0) {
			System.err.println("Error: Invalid Remote Port Specification " + "\n");
			return false;
		}
		if (remotehost == null) {
			System.err.println("Error: Invalid Remote Host Specification " + "\n");
			return false;
		}
		return true;
	}

	/**
	 * Shutdown the proxy if error occured due server connectivity.
	 */
	private static void shutdownProxy() {
		System.exit(-1);
	}

}
