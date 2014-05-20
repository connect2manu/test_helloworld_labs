
<%@ page import="com.manu.grails.guestbook.Feedback" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'feedback.label', default: 'Feedback')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'feedback.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'feedback.title.label', default: 'Title')}" />
                        
                            <g:sortableColumn property="feedback" title="${message(code: 'feedback.feedback.label', default: 'Feedback')}" />
                        
                            <th><g:message code="feedback.user.label" default="User" /></th>
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'feedback.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'feedback.lastUpdated.label', default: 'Last Updated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${feedbackInstanceList}" status="i" var="feedbackInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${feedbackInstance.id}">${fieldValue(bean: feedbackInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: feedbackInstance, field: "title")}</td>
                        
                            <td>${fieldValue(bean: feedbackInstance, field: "feedback")}</td>
                        
                            <td>${fieldValue(bean: feedbackInstance, field: "user")}</td>
                        
                            <td><g:formatDate date="${feedbackInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${feedbackInstance.lastUpdated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${feedbackInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
