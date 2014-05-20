package com.manu.demo.application.httpservlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.demo.application.al.AppStartupService;
import com.manu.demo.application.al.exceptions.InfrastructureException;

/**
 * Servlet implementation class BaseNotificationServlet
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(BaseServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		try {
			logger.info("+ destroy()");
			AppStartupService.getInstance().cleanup();
			logger.info("- destroy()");
		} catch (InfrastructureException e) {
			logger.error("destroy() | ERROR: " + e.getMessage());
			if (logger.isTraceEnabled()) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		logger.info("#### doPost() ####. Redirecting to doGet().");
		doGet(request, response);
	}

}
