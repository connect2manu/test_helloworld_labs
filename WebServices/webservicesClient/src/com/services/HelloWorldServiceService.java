/**
 * HelloWorldServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.services;

public interface HelloWorldServiceService extends javax.xml.rpc.Service {
    public java.lang.String getHelloWorldServiceAddress();

    public com.services.HelloWorldService getHelloWorldService() throws javax.xml.rpc.ServiceException;

    public com.services.HelloWorldService getHelloWorldService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
