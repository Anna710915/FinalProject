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

<jsp:useBean id="cart" scope="session" type="java.util.HashMap"/>

<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/CSS/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
    <title><fmt:message key="header.basket"/> </title>
</head>
<body>
<div class="page">
    <header>
        <%@include file="../header/header.jsp"%>
    </header>
    <div class="container justify-content-center col-12 col-sm-6 mt-3">
        <c:choose>
            <c:when test="${cart.isEmpty() eq 'false'}">
                <h3 class="text-center p-3"><fmt:message key="header.basket"/></h3>
                </br>
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th><fmt:message key="menu.product_name"/> </th>
                        <th><fmt:message key="menu.cost_with_product_discount"/> </th>
                        <th><fmt:message key="menu.number_products"/> </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${cart}">
                        <input type="hidden" value="<c:out value="${product.key.foodId}"/>"/>
                        <tr>
                            <td><c:out value="${product.key.nameFood}"/></td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${product.key.price - product.key.discount * product.key.price}"/> </td>
                            <td><c:out value="${product.value}"/> </td>
                            <td>
                                <form action="${absolutePath}/controller" method="post">
                                    <input type="hidden" name="command" value="delete_product_in_basket">
                                    <input type="hidden" name="id" value="${product.key.foodId}">
                                    <button type="submit" class="btn-danger"><fmt:message key="action.delete"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="mb-3"><fmt:message key="menu.product_price_with_user_discount"/> <c:out value="${total_price}"/> <fmt:message key="menu.product_money"/> </div>
                <form name="BasketForm" method="post" action="${absolutePath}/controller" class="needs-validation"novalidate>
                    <input type="hidden" name="command" value="create_order"/>
                    <input type="hidden" name="total_price" value="${total_price}"/>
                    </br>
                    <div class="form-group" class="mb-3">
                        <label class="form-label"><fmt:message key="order.address"/> </label>
                        <input type="text" name="address" class="form-control form-control-sm" value="" required pattern="^.{1,100}$">
                        <c:if test="${! empty invalid_order_address}">
                            <div class="invalid-feedback-backend" style="color: red">
                                <fmt:message key="${invalid_order_address}"/>
                            </div>
                        </c:if>
                        <div class="invalid-feedback">
                            <fmt:message key="order.invalid_address"/>
                        </div>
                    </div>
                    </br>
                    <div class="form-group" class="mb-3">
                    <select class="form-select" aria-label="Default select example" name="product_payment" required>
                        <option selected disabled value=""><fmt:message key="order.payment"/></option>
                        <option value="CARD"><fmt:message key="order.payment_card"/> </option>
                        <option value="CASH"><fmt:message key="order.payment_cash"/> </option>
                    </select>
                    <c:if test="${! empty invalid_order_payment}">
                        <div class="invalid-feedback-backend" style="color: red">
                            <fmt:message key="${invalid_order_payment}"/>
                        </div>
                    </c:if>
                        <div class="invalid-feedback">
                            <fmt:message key="order.invalid_payment"/>
                        </div>
                    </div>
                    </br>
                    <div class="form-group" class="mb-3">
                        <label class="form-label"><fmt:message key="order.comment"/></label>
                        <input type="text" name="user_comment" class="form-control form-control-sm" value="" pattern="^.{0,200}$">
                        <c:if test="${! empty invalid_order_comment}">
                            <div class="invalid-feedback-backend" style="color: red">
                                <fmt:message key="${invalid_order_comment}"/>
                            </div>
                        </c:if>
                        <div class="invalid-feedback">
                            <fmt:message key="order.invalid_comment"/>
                        </div>
                    </div>
                    </br>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"><fmt:message key="order.confirm"/></button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <h1><fmt:message key="order.empty"/> </h1>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="text-center">
        <ctg:footertag/>
    </div>
</div>
<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')

        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
