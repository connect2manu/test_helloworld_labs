package org.hibernate.ce.auction.seam.pojo;

import org.jboss.seam.annotations.*;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.Seam;
import org.hibernate.ce.auction.model.User;
import org.hibernate.ce.auction.dao.UserDAO;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@Name("login")
public class LoginAction {

    @In @Out(required = false)
    private User user;

    @In(create = true)
    private UserDAO userDAO;

    @In
    private Context sessionContext;

    @In
    private FacesContext facesContext;

    public String login() {
        User validatedUser = userDAO.validateLogin(user);
        if (validatedUser == null) {
            facesContext.addMessage(null, new FacesMessage("Invalid login"));
            return "login";
        } else {
            user = validatedUser;
            sessionContext.set(LoggedIn.LOGIN_TOKEN, true);
            return "start";
        }
    }

    public String logout() {
        Seam.invalidateSession(); // Mark Session as invalid

        // But also force a "logout" for the render response phase
        user = null;
        sessionContext.set(LoggedIn.LOGIN_TOKEN, null);

        return "restart";
    }

}
