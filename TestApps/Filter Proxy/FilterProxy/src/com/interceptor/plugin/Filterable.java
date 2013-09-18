package com.interceptor.plugin;

import java.net.Socket;

/**
 * Every client filter should implement this interface.
 */
public interface Filterable {
	/**
	 * Only filtered client requests should be allowed. <BR>
	 * Returns true, if allowed else returns false.
	 * 
	 * @param clientSocket
	 * @return
	 */
    public boolean filter(Socket clientSocket);
}
