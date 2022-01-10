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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
    <title><fmt:message key="header.orders"/> </title>
</head>
<body>
<div class="page">
    <header>
        <%@include file="../header/header.jsp"%>
    </header>
    <div class="container">
        <c:choose>
            <c:when test="${user.role eq 'CLIENT'}">
                <jsp:useBean id="order_list" scope="request" type="java.util.List"/>
                <c:choose>
                    <c:when test="${order_list.isEmpty()}">
                        <h3 class="text-center"><fmt:message key="order.empty_confirmed_order"/> </h3>
                    </c:when>
                    <c:otherwise>
                        <h3 class="text-center"><fmt:message key="order.confirmed"/> </h3>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="order.id"/></th>
                                <th scope="col"><fmt:message key="order.date_state_change"/> </th>
                                <th scope="col"><fmt:message key="order.state"/> </th>
                                <th scope="col"><fmt:message key="menu.product_price"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${order_list}">
                                <tr>
                                    <td><c:out value="${order.orderId}"/></td>
                                    <fmt:parseDate  value="${order.orderDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both"/>
                                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss" var="stdDatum" />
                                    <td><c:out value="${stdDatum}"/></td>
                                    <td><c:out value="${order.orderState}"/></td>
                                    <td><c:out value="${order.totalCost}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${user.role eq 'ADMIN'}">
                <jsp:useBean id="order_map" scope="request" type="java.util.HashMap"/>
                <c:choose>
                    <c:when test="${order_map.isEmpty()}">
                        <h3 class="text-center"><fmt:message key="order.empty_confirmed_order"/> </h3>
                    </c:when>
                    <c:otherwise>
                        <h3 class="text-center"><fmt:message key="order.confirmed"/> </h3>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="order.id"/></th>
                                <th scope="col"><fmt:message key="order.date_state_change"/> </th>
                                <th scope="col"><fmt:message key="order.state"/> </th>
                                <th scope="col"><fmt:message key="menu.product_price"/></th>
                                <th scope="col"><fmt:message key="order.payment"/></th>
                                <th scope="col"><fmt:message key="order.address"/></th>
                                <th scope="col"><fmt:message key="order.comment"/></th>
                                <th scope="col"><fmt:message key="registration.login"/></th>
                                <th scope="col"><fmt:message key="registration.phone"/> </th>
                                <th scope="col"><fmt:message key="admin.users_action"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${order_map}">
                                <tr>
                                    <td><c:out value="${order.key.orderId}"/></td>
                                    <fmt:parseDate  value="${order.key.orderDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both"/>
                                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss" var="stdDatum" />
                                    <td><c:out value="${stdDatum}"/></td>
                                    <td><c:out value="${order.key.orderState}"/></td>
                                    <td><c:out value="${order.key.totalCost}"/></td>
                                    <td><c:out value="${order.key.typePayment}"/> </td>
                                    <td><c:out value="${order.key.address}"/></td>
                                    <td><c:out value="${order.key.userComment}"/></td>
                                    <td><c:out value="${order.value.login}"/> </td>
                                    <td><c:out value="${order.value.phoneNumber}"/> </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                <fmt:message key="order.change_state"/>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" href="${absolutePath}/controller?command=change_order_state&state=PROCESSING&id=${order.key.orderId}">processing</a></li>
                                                <li><a class="dropdown-item" href="${absolutePath}/controller?command=change_order_state&state=COMPLETED&id=${order.key.orderId}">completed</a></li>
                                                <li><a class="dropdown-item" href="${absolutePath}/controller?command=change_order_state&state=RECEIVED&id=${order.key.orderId}">received</a></li>
                                                <li><a class="dropdown-item" href="${absolutePath}/controller?command=change_order_state&state=CANCELLED&id=${order.key.orderId}">cancelled</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
    <div class="text-center">
        <ctg:footertag/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
