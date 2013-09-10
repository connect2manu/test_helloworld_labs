package org.hibernate.ce.auction.seam.pojo;

import javax.ejb.AroundInvoke;
import javax.ejb.InvocationContext;

import org.jboss.seam.annotations.Around;
import org.jboss.seam.annotations.Within;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.interceptors.BijectionInterceptor;
import org.jboss.seam.interceptors.ConversationInterceptor;
import org.jboss.seam.interceptors.RemoveInterceptor;
import org.jboss.seam.interceptors.ValidationInterceptor;

@Around({BijectionInterceptor.class, ValidationInterceptor.class, ConversationInterceptor.class})
@Within(RemoveInterceptor.class)
public class LoggedInInterceptor {


    @AroundInvoke
    public Object checkLoggedIn(InvocationContext invocation) throws Exception {

        boolean isLoggedIn = Contexts.getSessionContext().get(LoggedIn.LOGIN_TOKEN) != null;

        System.out.println("### USER IS LOGGED IN " + isLoggedIn);

        if (isLoggedIn) {
            return invocation.proceed();
        } else {
            return "login";
        }
    }

}
