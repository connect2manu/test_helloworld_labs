package org.hibernate.ce.auction.web.filter;

import org.hibernate.ce.auction.dao.*;
import org.hibernate.ce.auction.model.Category;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;

/**
 * A servlet used to test the HibernateThread* filters in a non-managed environment.
 * <p>
 * If you can click through all conversation events in this servlet without
 * getting an exception, your servlet filter works.
 */
public class FilterTestServlet extends HttpServlet {

    public static final String ACTION_EVENT_ONE = "one";
    public static final String ACTION_EVENT_TWO = "two";
    public static final String ACTION_EVENT_THREE = "three";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        CategoryDAO dao = DAOFactory.HIBERNATE.getCategoryDAO();

        if (ACTION_EVENT_ONE.equals(action)) {

            Category cat = new Category("test");

            dao.makePersistent(cat);

            out.println("<br/>");
            out.println("<a href='filtertest?action=two&id=" + cat.getId() + "'>EventTwo</a>");

        } else if (ACTION_EVENT_TWO.equals(action)) {

            Long catId = new Long(request.getParameter("id"));

            Category loaded = dao.findById(catId, false);
            if (loaded == null) {
                throw new ServletException("Object not found!");
            }

            request.setAttribute(HibernateThreadExtendedFilter.END_OF_CONVERSATION_FLAG, true);

            out.println("<br/>");
            out.println("<a href='filtertest?action=three&id=" + catId + "'>EventThree</a>");

        } else if (ACTION_EVENT_THREE.equals(action)) {

            Long catId = new Long(request.getParameter("id"));
            Category loaded = dao.findById(catId, false);
            if (loaded == null) {
                throw new ServletException("Object not found!");
            }

            request.setAttribute(HibernateThreadExtendedFilter.END_OF_CONVERSATION_FLAG, true);

            out.println("<br/>");
            out.println("<a href='filtertest'>Start over</a>");

        } else {

            out.println("Start:</br>");
            out.println("<a href='filtertest?action=one'>EventOne</a>");
        }

        out.flush();
        out.close();
    }
}
