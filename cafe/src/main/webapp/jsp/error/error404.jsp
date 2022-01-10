<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.12.2021
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
    <br>
    Request From -> ${pageContext.errorData.requestURI}
    <hr/>
    Exception -> ${pageContext.exception}
    <hr/>
    Exception Status -> ${pageContext.errorData.statusCode}
    <hr/>
    Servlet Name -> ${pageContext.errorData.servletName}
    <hr/>
    <a href="${pageContext.request.contextPath}/index.jsp">backToStartPage</a>
</head>
<body>

</body>
</html>
