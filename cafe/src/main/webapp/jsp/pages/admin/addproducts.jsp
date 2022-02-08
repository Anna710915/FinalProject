<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.12.2021
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="absolutePath">${pageContext.request.contextPath}</c:set>
<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}" scope="session"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="${language = 'ru_RU'}" scope="session"/></c:when>
</c:choose>
<fmt:setBundle basename="context.language"/>
<html>
<head>
    <title><fmt:message key="menu.title"/> </title>
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
</head>
<body>
<div class="page">
    <header>
        <%@include file="../header/header.jsp"%>
    </header>
    <div class="container justify-content-center">
        <h3 class="text-center p-3"><fmt:message key="menu.new_product"/></h3>
        </br>
        <form name="AddProductFord" method="post" action="${absolutePath}/controller"  novalidate>
            <input type="hidden" name="command" value="insert_new_product"/>
            </br>
            <div class="form-group" class="mb-3">
                <label class="form-label"><fmt:message key="menu.product_name"/> </label>
                <input type="text" name="product_name" value="${param.product_name}" class="form-control" required pattern="^[A-Za-zА-Яа-я\s]{3,50}$">
                <c:choose>
                    <c:when test="${!empty invalid_product_name}">
                        <div class="invalid-feedback-backend" style="color: red">
                            <fmt:message key="${invalid_product_name}"/>
                        </div>
                        <div class="invalid-feedback">
                            <fmt:message key="menu.invalid_product_name"/>
                        </div>
                    </c:when>
                    <c:when test="${!empty not_uniq_product_name}">
                        <div class="invalid-feedback-backend" style="color: red">
                            <fmt:message key="${not_uniq_product_name}"/>
                        </div>
                        <div class="invalid-feedback">
                            <fmt:message key="menu.not_uniq_product_name"/>
                        </div>
                    </c:when>
                </c:choose>
            </div>
            </br>
            <div class="form-group" class="mb-3">
            <label class="form-label"><fmt:message key="menu.product_composition"/></label>
            <input type="text" name="product_composition" value="${fn:escapeXml(param.product_composition)}" class="form-control" pattern=".{0,200}">
                <c:if test="${!empty invalid_product_composition}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_product_composition}"/>
                    </div>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message key="menu.invalid_composition"/>
                </div>
            </div>
            </br>
            <div class="form-group" class="mb-3">
            <label class="form-label"><fmt:message key="menu.product_weight"/></label>
            <input type="text" name="product_weight" class="form-control" value="${param.product_weight}" required pattern="\d{1,6}(\.[0-9]{1,2})?">
                <div id="weightHelp" class="form-text"><fmt:message key="menu.weight_pattern"></fmt:message></div>
                <c:if test="${!empty invalid_product_weight}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_product_weight}"/>
                    </div>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message key="menu.invalid_product_weight"/>
                </div>
            </div>
            </br>
            <div class="form-group" class="mb-3">
            <label class="form-label"><fmt:message key="menu.product_calories"/></label>
                <input type="text" name="product_calories" class="form-control" value="${param.product_calories}" required pattern="\d{1,6}(\.[0-9]{2})?">
                <div id="weightHelp" class="form-text"><fmt:message key="menu.weight_pattern"></fmt:message></div>
                <c:if test="${!empty invalid_product_calories}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_product_calories}"/>
                    </div>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message key="menu.invalid_product_calories"/>
                </div>
                </div>
                </br>
            <div class="form-group" class="mb-3">
                <label class="form-label"><fmt:message key="menu.product_time"/></label>
                <input type="time" name="product_time" value="${param.product_time}" class="form-control">
                <c:if test="${!empty invalid_cooking_time}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_cooking_time}"/>
                    </div>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message key="menu.invalid_cooking_time"/>
                </div>
            </div>
            </br>
            <div class="form-group" class="mb-3">
                <label class="form-label"><fmt:message key="menu.product_discount"/></label>
                <input type="text" name="product_discount" value="${param.product_discount}" class="form-control" required pattern="0(\.\d{1,2})?">
                <div id="discountHelp" class="form-text"><fmt:message key="menu_discount_pattern"></fmt:message></div>
                <c:if test="${!empty invalid_product_discount}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_product_discount}"/>
                    </div>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message key="menu.invalid_product_discount"/>
                </div>
            </div>
            </br>
            <div class="form-group" class="mb-3">
                <label class="form-label"><fmt:message key="menu.product_cost"/></label>
                <input type="text" name="product_price" value="${param.product_price}" class="form-control" required pattern="\d{1,6}(\.[0-9]{1,2})?">
                <div id="costHelp" class="form-text"><fmt:message key="menu.cost_pattern"></fmt:message></div>
                <c:if test="${!empty invalid_product_price}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_product_price}"/>
                    </div>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message key="menu.invalid_product_price"/>
                </div>
            </div>
            </br>
            <select class="form-select" aria-label="Default select example" name="product_section">
                <option selected disabled><fmt:message key="menu.product_section"/></option>
                <c:forEach var="item" items="${applicationScope.section_list}">
                    <option value="${item.sectionId}">${item.sectionName}</option>
                </c:forEach>
            </select>
            <c:if test="${!empty invalid_product_section}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_product_section}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="menu.invalid_product_section"/>
            </div>
            </br>
            <div class="text-center mb-3">
                <button type="submit" class="btn btn-primary"><fmt:message key="menu.insert_menu"/> </button>
            </div>
        </form>

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