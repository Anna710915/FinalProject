<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="absolutePath">${pageContext.request.contextPath}</c:set>
<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}" scope="session"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="${language = 'ru_RU'}" scope="session"/></c:when>
</c:choose>
<fmt:setBundle basename="context.language"/>
<html>
<head>
    <title>Error 500</title>
</head>
<body>
<div class="page">
    <header>
        <%@include file="../pages/header/header.jsp"%>
    </header>
    <div class="container justify-content-center">
        <img src="picture/img_2.png" class="img-fluid" alt="no image">
    </div>
    <div class="text-center">
        <ctg:footertag/>
    </div>
</div>
</body>
</html>
