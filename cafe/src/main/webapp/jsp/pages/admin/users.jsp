<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.12.2021
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="${absolutePath}/CSS/styles.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
    <title><fmt:message key="admin.title_users"/> </title>

</head>
<body>

<div class="page">
    <header>
        <%@include file="../header/header.jsp"%>
    </header>
<div class="row">
    <div class="container">
        <h3 class="text-center"><fmt:message key="admin.list_users"/> </h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="registration.first_name"/> </th>
                <th><fmt:message key="registration.last_name"/> </th>
                <th><fmt:message key="registration.login"/> </th>
                <th><fmt:message key="registration.email"/> </th>
                <th><fmt:message key="registration.enter_phone"/> </th>
                <th><fmt:message key="admin.users_state"/> </th>
                <th><fmt:message key="admin.users_action"/> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${list_user}">
                <input type="hidden" value="<c:out value="${user.userId}"/>"/>
                <tr>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.phoneNumber}"/></td>
                    <td><c:out value="${user.state}"/></td>
                   <td>
                    <div class="row">
                        <div class="col">
                       <form action="${absolutePath}/controller" method="post">
                           <input type="hidden" name="command" value="delete_user">
                           <input type="hidden" name="id" value="${user.userId}">
                           <button type="submit" class="btn-danger"><fmt:message key="action.delete"/></button>
                       </form>
                        </div>
                        <div class="col">
                        <form action="${absolutePath}/controller" method="post">
                            <input type="hidden" name="id" value="${user.userId}">
                       <c:choose>
                           <c:when test="${user.state eq 'BLOCKED'}">

                                   <input type="hidden" name="command" value="unblock_user">
                                   <button type="submit" class="btn-dark"><fmt:message key="admin.unblock_action"/></button>
                           </c:when>
                           <c:otherwise>
                                   <input type="hidden" name="command" value="block_user">
                                   <button type="submit" class="btn-dark"><fmt:message key="admin.block_action"/></button>
                           </c:otherwise>
                       </c:choose>
                        </form>
                        </div>
                    </div>
                   </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
    <div class="text-center">
        <ctg:footertag/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
