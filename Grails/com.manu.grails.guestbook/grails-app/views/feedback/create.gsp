

<%@ page import="com.manu.grails.guestbook.Feedback" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${feedbackInstance}">
            <div class="errors">
                <g:renderErrors bean="${feedbackInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title"><g:message code="feedback.title.label" default="Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedbackInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" maxlength="80" value="${feedbackInstance?.title}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="feedback"><g:message code="feedback.feedback.label" default="Feedback" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedbackInstance, field: 'feedback', 'errors')}">
                                    <g:textArea name="feedback" cols="40" rows="5" value="${feedbackInstance?.feedback}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user"><g:message code="feedback.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedbackInstance, field: 'user', 'errors')}">
                                    <g:select name="user.id" from="${com.manu.grails.guestbook.User.list()}" optionKey="id" value="${feedbackInstance?.user?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
