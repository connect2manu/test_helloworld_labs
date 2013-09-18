package com.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import com.services.HelloWorldService;
import com.services.HelloWorldServiceServiceLocator;
import com.services.HelloWorldServiceSoapBindingStub;

/**
 * Servlet implementation class for Servlet: Client
 *
 */
 public class Client extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Client() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		      // EndPoint URL for the SparePartPrice web service.
		      String endpointURL =
		        "http://localhost:8080/web/services/TestWeb?wsdl";
		      // Method Name to invoke for the SparePartPrice web service
		      String methodName  = "getMessage";
		      // Create the Service call
		      Service service = new Service();
		      Call call = (Call) service.createCall();
		      call.setTargetEndpointAddress(new java.net.URL(endpointURL));
		      call.setOperationName(new QName("getMessage",methodName));
		      //call.addParameter("sku",XMLType.XSD_STRING, ParameterMode.IN);
		      call.setReturnType(XMLType.XSD_STRING);
		      //Setup the Parameters i.e. the Part SKU to be passed as input
		      //parameter to the SparePartPrice web service
		      //Object[] params = new Object[] {"SKU-123"};

		      //Invoke the SparePartPrice web service
		      System.out.println(call.invoke(new Object[]{}));
		      
		      //Print out the result
//		      HelloWorldServiceServiceLocator serviceLocator = new HelloWorldServiceServiceLocator();
//		      HelloWorldServiceSoapBindingStub stub  = (HelloWorldServiceSoapBindingStub) serviceLocator.getHelloWorldService();
//		      
//		      System.out.println("The message is:"+stub.getMessage());
		      }
		    catch (Exception e) {
		      System.out.println(e.toString());
		    }

	}  	  	  	    
}