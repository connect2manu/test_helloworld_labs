/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interceptor.plugin.example.ipfilter;

import com.interceptor.plugin.Filterable;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 *
 * @author arpit.agarwal
 */
public class IPFilter implements Filterable {

    Properties ipProperties;
    private static IPFilter instance = null; 
    
    private IPFilter(){ 
        ipProperties = new Properties();
        try {
            ipProperties.load(this.getClass().getClassLoader().getResourceAsStream("com/interceptor/plugin/example/ipfilter/config/ipconfig.properties"));
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
            for(int index = 0; index<allowedIPList.length; index++) {
                if(clientIPAddress.equals(allowedIPList[index].trim())) {
                    return true;
                }
            }
            return false;
        }
        
        if(disallowedIPs!=null){
            String[] disallowedIPList = disallowedIPs.split(",");
            for(int index = 0; index<disallowedIPList.length; index++) {
                if(clientIPAddress.equals(disallowedIPList[index].trim())) {
                    return false;
                }
            }
            return true;
        }
        
        //in cases where nothing is defined in properties
        return true;
    }
}