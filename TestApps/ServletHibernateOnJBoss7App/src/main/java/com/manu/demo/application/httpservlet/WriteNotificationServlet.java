package com.manu.demo.application.httpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.exceptions.BusinessException;
import com.manu.demo.application.al.exceptions.InfrastructureException;
import com.manu.demo.application.delegate.WriteNotificationHelper;
import com.manu.demo.application.delegate.WriteRequestVO;
import com.manu.demo.application.util.AppUtils;



/**
 * Handles all writeNotification GET requests coming from TM & URH.<br>
 * <br>
 * For Example:<br>
 * GET<br>
 * /notificationservice/writeNotification?MACAddress=0014F8E3537D&NotificationType=T_NPVR&
 * TimeStamp=1356606171694<br>
 */
@WebServlet("/writeNotification")
public class WriteNotificationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(WriteNotificationServlet.class);

	/**
	 * Default constructor.
	 */
	public WriteNotificationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("+++++++++++++++++++++++ WriteNotification ++++++++++++++++++++++");
		if(logger.isDebugEnabled()) {
			logger.debug("WriteNotification request QueryString: " + request.getQueryString());
		}
		response.setContentType("text/html");

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
		BigInteger bigMACAddress;
		try {
			bigMACAddress = AppUtils.convertStringToBigInt(macAddress);
		} catch (NumberFormatException e) {
			logger.error("Invalid MACAddress - " + macAddress);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid MACAddress - " + macAddress);
			return;
		}

		String notificationType = request.getParameter("NotificationType");
		if (notificationType == null) {
			logger.error("Missing parameter NotificationType");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter NotificationType");
			return;
		}
		if ("".equals(notificationType.trim())) {
			logger.error("Parameter NotificationType should not be empty.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty NotificationType");
			return;
		}
		if (!AppUtils.isValidNotificationType(notificationType)) {
			logger.error("Invalid NotificationType - " + notificationType);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid NotificationType value");
			return;
		}

		String timestampStr = request.getParameter("TimeStamp");
		if (timestampStr == null) {
			logger.error("Missing parameter TimeStamp.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter TimeStamp");
			return;
		}
		if ("".equals(timestampStr.trim())) {
			logger.error("Parameter TimeStamp should not be empty.");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty TimeStamp");
			return;
		}
		long timestampLong;
		try {
			timestampLong = AppUtils.convertStringToTimestamp(timestampStr);
		} catch (BusinessException bex) {
			logger.error("Invalid TimeStamp value - " + timestampStr + ". " + bex.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, bex.getMessage());
			return;
		}

		PrintWriter writer = null;

		try {
			// String timestampFormat = "yyyy-MM-dd hh:mm:ss.SSSSSS";
			// SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);
			Date timestampDate = new Date(timestampLong);
			if(logger.isInfoEnabled()) {
				logger.info("doGet() | request:: " + " MACAddress = " + macAddress + ", ConvertedBigMAC = " + bigMACAddress
						+ ", TimeStamp = " + timestampStr
						+ ", TimestampDate = " + timestampDate.getTime() /*+ ", FormattedTimestamp = " + sdf.format(timestampDate)*/
						+ ", NotificationType = " + notificationType);
			}

			WriteRequestVO notificationData = new WriteRequestVO(bigMACAddress, notificationType, timestampDate);

			String message = null;
			boolean isNew = WriteNotificationHelper.getInstance().process(notificationData);
			response.setStatus(HttpServletResponse.SC_OK);
			logger.debug("Write notification processed successfully to persistent store.");
			
			if (isNew) {
				message = "Notification inserted successfully.";
			} else {
				message = "Notification updated successfully.";
			}

			writer = response.getWriter();
			printResponse(response, writer, message);
		} catch (BusinessException be) {
			logger.error("INTERNAL_SERVER_ERROR: Business rule violation is caused. " + be.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, be.getMessage());
			logger.error("ERROR:" + be.getMessage());
		} catch (InfrastructureException ie) {
			logger.error("INTERNAL_SERVER_ERROR: " + ie.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ie.getMessage());
			logger.error("ERROR:" + ie.getMessage());
			if (logger.isTraceEnabled()) {
				ie.printStackTrace();
			}
		} catch (Exception e) {
			logger.error("INTERNAL_SERVER_ERROR: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			if (logger.isTraceEnabled()) {
				e.printStackTrace();
			}
		} finally {
			if (writer != null) {
				writer.close();
			}
			logger.info("----------------------- WriteNotification --------------------------");
		}
	}

	/**
	 * Prints the successful response messages.
	 * 
	 * @param response
	 * @param writer
	 * @param msg
	 */
	private void printResponse(HttpServletResponse response, PrintWriter writer, String msg) {
		writer.println("<html><head>");
		writer.println("Status : ");
		writer.println(response.getStatus()+"<br/>");
		writer.println("Content Type : ");
		writer.println(response.getContentType()+"<br/>");
		writer.println("Charset : ");		
		writer.println(response.getCharacterEncoding()+"<br/>");
		writer.println("Message : " + msg);
		writer.println("</body></html>");
	}

}
