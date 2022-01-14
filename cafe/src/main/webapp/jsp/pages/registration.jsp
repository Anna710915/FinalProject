<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.12.2021
  Time: 22:55
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
<fmt:message key="registration.birthday" var="user_birthday"/>
<fmt:message key="registration.email" var="user_email"/>
<fmt:message key="registration.first_name" var="user_first_name"/>
<fmt:message key="registration.last_name" var="user_last_name"/>
<fmt:message key="registration.login" var="user_login"/>
<fmt:message key="registration.password" var="user_pass"/>
<fmt:message key="registration.phone" var="user_phone"/>
<fmt:message key="registration.name" var="reg_name"/>
<fmt:message key="registration.enter_birthday" var="e_birthday"/>
<fmt:message key="registration.enter_email" var="e_email"/>
<fmt:message key="registration.enter_first_name" var="e_first_name"/>
<fmt:message key="registration.enter_last_name" var="e_last_name"/>
<fmt:message key="registration.enter_login" var="e_login"/>
<fmt:message key="registration.enter_password" var="e_password"/>
<fmt:message key="registration.enter_phone" var="e_phone"/>

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
    <title>${reg_name}</title>

</head>
<body>
<div class="page">
    <header>
        <%@include file="header/header.jsp"%>
    </header>
<div class="container justify-content-center col-12 col-sm-6 mt-3">
    <h3 class="text-center p-3">${reg_name}</h3>
    <form role="form" action="${absolutePath}/controller" method="post" class="needs-validation" novalidate>
        <input type="hidden" name="command" value="registration"/>
        <div class="row gy-3">
        <div class="form-group">
            <label class="form-label">${user_first_name}</label>
            <input type="text" name="first_name" class="form-control" placeholder="${e_first_name}" required pattern="^[A-Za-zА-Яа-я]{3,50}$">
            <c:if test="${!empty invalid_first_name}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_first_name}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_first_name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">${user_last_name}</label>
            <input type="text" name="last_name" class="form-control" placeholder="${e_last_name}" required pattern="^[A-Za-zА-Яа-я]{3,50}$">
            <c:if test="${!empty invalid_last_name}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_last_name}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_last_name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">${user_email}</label>
            <input type="email" name="email" class="form-control form-control-sm" placeholder="${e_email}" required pattern="^[A-Za-z0-9\.]{1,30}@[a-z]{2,7}\.[a-z]{2,4}$">
            <div id="emailHelp" class="form-text"><fmt:message key="registration.correct_gmail"></fmt:message></div>
            <c:if test="${!empty invalid_email}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_email}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_mail"/>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">${user_phone}</label>
            <input type="text" name="phone_number" class="form-control" placeholder="${e_phone}" required pattern="(29|25|44)\d{7}">
            <div id="phoneHelp" class="form-text"><fmt:message key="registration.correct_phone_number"></fmt:message></div>
            <c:if test="${!empty invalid_phone_number}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_phone_number}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_phone_number"/>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">${user_birthday}</label>
            <input type="text" name="birthday" class="form-control" placeholder="${e_birthday}" required pattern="^(19|20)\d\d-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$">
            <div id="dateHelp" class="form-text"><fmt:message key="registration.correct_date"/></div>
            <c:if test="${!empty invalid_birthday}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_birthday}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_birthday"/>
            </div>
        </div>
        <div class="form-group" >
            <label class="form-label">${user_login}</label>
            <input type="text" name="login" class="form-control" placeholder="${e_login}" required pattern="^[A-Za-zА-Яа-я0-9_]{4,16}$">
            <div id="loginHelp" class="form-text"><fmt:message key="registration.correct_login"></fmt:message></div>
            <c:if test="${!empty invalid_login}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_login}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_login"/>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">${user_pass}</label>
            <input type="password" name="password" class="form-control form-control-sm" placeholder="${e_password}" required pattern="^[A-Za-zА-Яа-я0-9\.]{5,40}$">
            <div id="passHelp" class="form-text"><fmt:message key="registration.correct_password"></fmt:message></div>
            <c:if test="${!empty invalid_password}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${invalid_password}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                <fmt:message key="registration.invalid_password"/>
            </div>
        </div>
        <div class="text-center mb-3">
            <button type="submit" class="btn btn-success"><fmt:message key="registration.submit"/></button>
        </div>
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
