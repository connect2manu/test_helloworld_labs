package com.acn.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.annotation.WebServlet;


// @WebServlet("/test")
public class PerformanceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PerformanceTestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+ inside PerformanceTestServlet | doGet()");
		/*		response.setContentType("text/html");
				PrintWriter writer = null;

				try {
					writer = response.getWriter();
					printResponse(response, writer, "Test Response");
				} catch (Exception e) {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
				} finally {
					if (writer != null) {
						writer.close();
					}
				}*/
	}


}
