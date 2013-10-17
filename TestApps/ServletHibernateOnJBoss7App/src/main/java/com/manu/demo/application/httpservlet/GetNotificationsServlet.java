package com.manu.demo.application.httpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.exceptions.BusinessException;
import com.manu.demo.application.al.exceptions.InfrastructureException;
import com.manu.demo.application.delegate.GetNotificationHelper;
import com.manu.demo.application.util.AppUtils;

/**
 * Handles all getNotifications GET requests coming from client devices.<br>
 * <br>
 * For Example:<br>
 * GET<br>
 * /notificationservice/getNotification?MACAddress=0014F8E3537D<br>
 */
@WebServlet("/getNotification")
public class GetNotificationsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	final Logger logger = LoggerFactory.getLogger(GetNotificationsServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetNotificationsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("+++++++++++++++++++++++ Retrieve Notifications ++++++++++++++++++++++");
		if(logger.isDebugEnabled()) {
			logger.debug("GetNotifications request QueryString: " + request.getQueryString());
		}

		response.setContentType("text/xml");

		String macAddress = request.getParameter("MACAddress");
		if (macAddress == null) {
			logger.error("Missing parameter MACAddress.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter MACAddress");
			return;
		}
		if ("".equals(macAddress.trim())) {
			logger.error("Parameter MACAddress should not be empty.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty MACAddress");
			return;
		}
		BigInteger bigMacAddress;
		try {
			bigMacAddress = AppUtils.convertStringToBigInt(macAddress);
		} catch (NumberFormatException e) {
			logger.error("Invalid MACAddress - " + macAddress);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid MACAddress - " + macAddress);
			return;
		}

		if(logger.isInfoEnabled()) {
			logger.info("doGet() | request:: " + " MACAddress = " + macAddress + ", ConvertedBigMAC = "
					+ bigMacAddress);
		}

		GetNotificationHelper reqProcessor = new GetNotificationHelper();
		PrintWriter resWriter = null;
		String xml = null;
		try {
			xml = reqProcessor.process(bigMacAddress);
			response.setStatus(HttpServletResponse.SC_OK);
			resWriter = response.getWriter();
			logger.debug("Notifications retrieved successfully.");
		} catch (BusinessException be) {
			logger.error("INTERNAL_SERVER_ERROR: Business rule violation is caused. " + be.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, be.getMessage());
		} catch (InfrastructureException ie) {
			logger.error("INTERNAL_SERVER_ERROR: " + ie.getMessage());
			if (logger.isTraceEnabled()) {
				ie.printStackTrace();
			}
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ie.getMessage());
		} catch (Exception e) {
			logger.error("INTERNAL_SERVER_ERROR: " + e.getMessage());
			if (logger.isTraceEnabled()) {
				e.printStackTrace();
			}
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		} finally {
			if(logger.isInfoEnabled()) {
				logger.info("Notification server response XML :: " + xml);
			}
			if (xml != null) {
				resWriter.println(xml);
			}
			if (resWriter != null) {
				resWriter.close();
			}
			logger.info("----------------------- Retrieve Notifications --------------------------");
		}
	}

}
