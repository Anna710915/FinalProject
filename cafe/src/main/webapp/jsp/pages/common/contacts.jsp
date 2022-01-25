<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="customtags" %>
<c:set var="absolutePath">${pageContext.request.contextPath}</c:set>
<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}" scope="session"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="${language = 'ru_RU'}" scope="session"/></c:when>
</c:choose>
<fmt:setBundle basename="context.language"/>
<html>

<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/CSS/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
    <title><fmt:message key="header.contacts"/> </title>
</head>
<body>
<div class="page">
    <header>
        <%@include file="../header/header.jsp"%>
    </header>

    <div class="container justify-content-center" style="align-content: center; padding-top: 70px">
        <p class="text-center">
            <fmt:message key="order.address"/>
        <p class="text-center">
            <fmt:message key="cafe.address"/>
        </p>
        <p class="text-center">
            +375 29 135 98 35
        </p>
        <p class="text-center">
            merkulanna7@gmail.com
        </p>
        <p class="text-center">
            <fmt:message key="cafe.work"/>
            <fmt:message key="cafe.delivery"/> : 13:00-22:30
        </p>
        <p class="text-center">
            Cafe:
            Вс - Чт 13:00-00:00
            Пт - Сб 13:00-01:00
        </p>
        <p class="text-center">
            <fmt:message key="cafe.booking"/>
            +375 29 135 98 35
        </p>


    </div>
    <div class="text-center">
        <ctg:footertag/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
