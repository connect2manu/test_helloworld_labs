package com.plugins.ipfilter;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import com.interceptor.plugin.Filterable;

/**
 * Sample implementation for IP based filtering.
 */
public class IPFilter implements Filterable {

    Properties ipProperties;
    private static IPFilter instance = null; 
    
    private IPFilter(){ 
        ipProperties = new Properties();
        try {
			ipProperties.load(this.getClass().getClassLoader()
					.getResourceAsStream("com/plugins/ipfilter/config/ipconfig.properties"));
        } catch (IOException ex) {
            System.out.println("Error loading configuration properties. Filter not set.");
        }
    }

    public static IPFilter getInstance() {
        if(instance==null) {
            instance = new IPFilter();
        }
        return instance;
    }
    
    @Override
    public boolean filter(Socket clientSocket) {
        String allowedIPs = ipProperties.getProperty("allowedIPs");
        String disallowedIPs = ipProperties.getProperty("disallowedIPs");
        String clientIPAddress = clientSocket.getInetAddress().getHostAddress();
        System.out.println(clientIPAddress);
        
        if(allowedIPs!=null) {
            String[] allowedIPList = allowedIPs.split(",");
            for (String element : allowedIPList) {
                if(clientIPAddress.equals(element.trim())) {
                    return true;
                }
            }
            return false;
        }
        
        if(disallowedIPs!=null){
            String[] disallowedIPList = disallowedIPs.split(",");
            for (String element : disallowedIPList) {
                if(clientIPAddress.equals(element.trim())) {
                    return false;
                }
            }
            return true;
        }
        
        //in cases where nothing is defined in properties
        return true;
    }
}