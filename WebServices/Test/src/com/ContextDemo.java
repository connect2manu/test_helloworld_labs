package com;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import be.belgacom.tv.uusd.facade.proxy.uusd.UUSDBean;

import com.nsn.urh.beans.common.facade.URHServiceBean;

public class ContextDemo {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
/*		Hashtable<String,String> props = new Hashtable<String, String>();
        props.put("java.naming.provider.url", "jnp://10.125.133.150:1099");
        props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        props.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
               
        //props.put(key, value);
        
        InitialContext ic = new InitialContext(props);
        Object obj=ic.lookup("URHServiceBean/remote");
        System.out.println(obj);
        
        URHServiceBean remoteBean = (URHServiceBean)obj;
        System.out.println(remoteBean);
        
        boolean success = remoteBean.authenticateSTB("0014F8E3544D");
        System.out.println("success = "+success);*/
        
        /****************************************************************************/
        
		Hashtable<String,String> props2 = new Hashtable<String, String>();
        props2.put("java.naming.provider.url", "jnp://10.125.133.161:1099");
        props2.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props2.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        props2.put("jnp.socket.Factory", "org.jnp.interfaces.TimedSocketFactory");
               
        //props.put(key, value);
        
        InitialContext ic = new InitialContext(props2);
        Object obj = ic.lookup("proxy/uusd/UUSDBean");
        System.out.println(obj);
        
        UUSDBean remoteBean = (UUSDBean)obj;
        System.out.println("UUSD remoteBean = "+remoteBean);
        
        Map<String, Object> userMap  = new HashMap<String, Object>();
        userMap.put("UserGID", "1006");
        Map result = remoteBean.getUser(userMap);
        System.out.println("GetUser | result = "+result);
        
	}

}

