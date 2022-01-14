<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="absolutePath">${pageContext.request.contextPath}</c:set>
<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}" scope="session"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="${language = 'ru_RU'}" scope="session"/></c:when>
</c:choose>
<fmt:setBundle basename="context.language"/>

<fmt:message key="header.brand" var="brand"/>
<fmt:message key="header.about_us" var="about_us"/>
<fmt:message key="header.contacts" var="contacts"/>
<fmt:message key="header.language" var="lang"/>
<fmt:message key="header.main" var="main"/>
<fmt:message key="header.sign_in" var="login"/>
<fmt:message key="header.basket" var="basket"/>
<fmt:message key="header.menu" var="menu"/>
<fmt:message key="header.profile" var="profile"/>
<fmt:message key="header.sign_out" var="logout"/>
<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/CSS/styles.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="height: 50px">
    <div class="container-fluid" style="height: 100px">
        <a class="navbar-brand" href="#">${brand}</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link active" href="${absolutePath}/jsp/pages/home.jsp">${main}</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">${about_us}</a></li>
                        <li class="nav-item"><a class="nav-link" href="${absolutePath}/jsp/pages/common/contacts.jsp">${contacts}</a></li>

                          <c:choose>
                              <c:when test="${user.role eq 'ADMIN'}"><%@include file="fragment/admin_header.jspf" %></c:when>
                              <c:when test="${user.role eq 'CLIENT'}"><%@include file="fragment/client_header.jspf" %></c:when>
                          </c:choose>


            </ul>
        </div>
                <div>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${language eq 'ru_RU'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${absolutePath}/controller?command=change_language&language=en_US">${lang}</a>
                                </li>
                            </c:when>
                            <c:when test="${language eq 'en_US'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${absolutePath}/controller?command=change_language&language=ru_RU">${lang}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="${absolutePath}/controller?command=change_language&language=ru_RU">${lang}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    <c:choose>
                        <c:when test="${user.role eq 'ADMIN' or user.role eq 'CLIENT'}">
                            <li class="nav-item"><a class="nav-link" href="${absolutePath}/controller?command=sign_out"> ${logout}</a></li>
                        </c:when>
                        <c:otherwise>
                                <li class="nav-item"><a class="nav-link" href="${absolutePath}/jsp/pages/signIn.jsp">${login}</a></li>
                        </c:otherwise>
                    </c:choose>
                    </ul>
                </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
