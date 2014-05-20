<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Working with cookies demo</title>
</head>

<body>
<h1>Working with cookies demo</h1>
Cookie name: username<br/>

<h2>Get cookie by standard &lt;g:cookie/&gt; tag</h2>
<pre>
    &lt;g:cookie name="username"/&gt;
</pre>
Result: <g:cookie name="username"/>

<h2>Get cookie by &lt;cookie:get/&gt; tag</h2>
<pre>
    &lt;cookie:get name="username"/&gt;
</pre>
Result: <cookie:get name="username"/>

<h2>Get cookie by cookieService.getCookie(String name)</h2>
Result: ${cookieValueFromService}

<h2>Get cookie by request.getCookie(String name)</h2>
Result: ${cookieValueFromResponse}

<h2>Set cookie by cookieService.setCookie(String name, String value, Integer maxAge)</h2>
<g:form action="setCookie1" method="post">
    <g:textField name="cookieValue" value="${g.cookie(name: 'username')}"/>
    <input type="submit">
</g:form>

<h2>Set cookie by cookieService.setCookie(Cookie cookie)</h2>
<g:form action="setCookie2" method="post">
    <g:textField name="cookieValue" value="${g.cookie(name: 'username')}"/>
    <input type="submit">
</g:form>

<h2>Set cookie by response.setCookie(String name, String value, Integer maxAge)</h2>
<g:form action="setCookie3" method="post">
    <g:textField name="cookieValue" value="${g.cookie(name: 'username')}"/>
    <input type="submit">
</g:form>

<h2>Delete cookie by cookieService.deleteCookie(String name)</h2>
<g:form action="deleteCookie1" method="post">
    <input type="submit">
</g:form>

<h2>Delete cookie by response.deleteCookie(String name)</h2>
<g:form action="deleteCookie2" method="post">
    <input type="submit">
</g:form>



</body>
</html>