package org.hibernate.ce.auction.seam.pojo;

import static java.lang.annotation.ElementType.TYPE;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.ejb.Interceptor;
import java.lang.annotation.*;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Interceptor(LoggedInInterceptor.class)
public @interface LoggedIn {
    public static final String LOGIN_TOKEN  = "loggedIn";
}