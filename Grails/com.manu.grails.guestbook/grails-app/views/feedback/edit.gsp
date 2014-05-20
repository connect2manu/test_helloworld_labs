

<%@ page import="com.manu.grails.guestbook.Feedback" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${feedbackInstance}">
            <div class="errors">
                <g:renderErrors bean="${feedbackInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${feedbackInstance?.id}" />
                <g:hiddenField name="version" value="${feedbackInstance?.version}" />
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
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comments"><g:message code="feedback.comments.label" default="Comments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: feedbackInstance, field: 'comments', 'errors')}">
                                    
<ul>
<g:each in="${feedbackInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="comment" action="create" params="['feedback.id': feedbackInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
