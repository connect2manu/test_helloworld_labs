/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interceptor.plugin;

import java.net.Socket;

/**
 *
 * @author arpit.agarwal
 */
public interface Filterable {
    public boolean filter(Socket clientSocket);
}
