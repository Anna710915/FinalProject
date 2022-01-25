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
    <title><fmt:message key="profile.settings"/></title>
</head>
<body>
<div class="page">
    <header>
        <%@include file="../header/header.jsp"%>
    </header>
    <c:choose>
        <c:when test="${param.result eq 'true'}">
            <div class="alert alert-success" role="alert" id="message"><fmt:message key="action.result_success"/></div>
        </c:when>
    </c:choose>
    <div class="row">
        <h3 class="text-center p-3"><fmt:message key="profile.settings"/></h3>
        <form role="form" action="${absolutePath}/controller" method="post"  novalidate>
            <input type="hidden" name="command" value="update_user_profile">
        <div class="container col-12 col-sm-6 mt-3">
            <dl class="row my-3">
                <dt class="col-2 mb-3">
                    <fmt:message key="registration.first_name"/>
                </dt>
                <div class="form-group col-10 mb-3">
                    <input type="text" name="first_name" value="${user.firstName}" class="form-control" placeholder="${e_first_name}" required pattern="^[A-Za-zА-Яа-я]{3,50}$">
                    <c:if test="${!empty invalid_first_name}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${invalid_first_name}"/>
                    </div>
                    </c:if>
                    <div class="invalid-feedback">
                        <fmt:message key="registration.invalid_first_name"/>
                    </div>
                </div>
                <dt class="col-2 mb-3">
                    <fmt:message key="registration.last_name"/>
                </dt>
                <div class="form-group col-10 mb-3">
                    <input type="text" name="last_name" value="${user.lastName}" class="form-control" placeholder="${e_last_name}" required pattern="^[A-Za-zА-Яа-я]{3,50}$">
                    <c:if test="${!empty invalid_last_name}">
                        <div class="invalid-feedback-backend" style="color: red">
                            <fmt:message key="${invalid_last_name}"/>
                        </div>
                    </c:if>
                    <div class="invalid-feedback">
                        <fmt:message key="registration.invalid_last_name"/>
                    </div>
                </div>
                <dt class="col-2 mb-3">
                    <fmt:message key="registration.email"/>
                </dt>
                <div class="form-group col-10 mb-3">
                    <input type="email" name="email" value="${user.email}" class="form-control" placeholder="${e_email}" required pattern="^[A-Za-z0-9\.]{1,30}@[a-z]{2,7}\.[a-z]{2,4}$">
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
                <dt class="col-2 mb-3">
                    <fmt:message key="registration.birthday"/>
                </dt>
                <div class="form-group col-10 mb-3">
                    <input type="date" name="birthday" value="${user.birthday}" class="form-control" placeholder="${e_birthday}" required >
                    <c:if test="${!empty invalid_birthday}">
                        <div class="invalid-feedback-backend" style="color: red">
                            <fmt:message key="${invalid_birthday}"/>
                        </div>
                    </c:if>
                    <div class="invalid-feedback">
                        <fmt:message key="registration.invalid_birthday"/>
                    </div>
                </div>
                <dt class="col-2 mb-3">
                    <fmt:message key="registration.phone"/>
                </dt>
                <div class="form-group col-10 mb-3">
                    <input type="text" name="phone_number" value="${user.phoneNumber}" class="form-control" placeholder="${e_phone}" required pattern="(29|25|44|33)\d{7}">
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
                <div class="text-center">
                    <button type="submit" class="btn btn-success"><fmt:message key="profile.update"/></button>
                </div>
            </dl>
        </div>
        </form>

        <h3 class="text-center p-3"><fmt:message key="profile.change_password"/></h3>
        <form role="form" action="${absolutePath}/controller" method="post" class="needs-validation" novalidate>
            <input type="hidden" name="command" value="change_password">
            <div class="container col-12 col-sm-6 mt-3">
                <dl class="row my-3">
                    <div class="form-group mb-3">
                        <label class="form-label"><fmt:message key="profile.enter_old_password"/></label>
                        <input type="password" name="old_password" class="form-control" required pattern="^[A-Za-zА-Яа-я0-9\.]{5,40}$">
                        <div id="passHelp" class="form-text"><fmt:message key="registration.correct_password"></fmt:message></div>
                        <c:if test="${!empty invalid_old_password}">
                            <div class="invalid-feedback-backend" style="color: red">
                                <fmt:message key="registration.invalid_password"/>
                            </div>
                        </c:if>
                        <div class="invalid-feedback">
                            <fmt:message key="registration.invalid_password"/>
                        </div>
                    </div>
                    <div class="form-group mb-3">
                        <label class="form-label"><fmt:message key="profile.enter_new_password"/></label>
                        <input type="password" name="new_password" class="form-control" required pattern="^[A-Za-zА-Яа-я0-9\.]{5,40}$">
                        <div id="passHelp" class="form-text"><fmt:message key="registration.correct_password"></fmt:message></div>
                        <div class="invalid-feedback">
                            <fmt:message key="profile.invalid_repeat_password"/>
                        </div>
                        <c:if test="${!empty invalid_new_password}">
                            <div class="invalid-feedback-backend" style="color: red">
                                <fmt:message key="registration.invalid_password"/>
                            </div>
                        </c:if>
                        <c:if test="${! empty invalid_new_uniq_password}">
                            <div class="invalid-feedback-backend" style="color: red">
                                <fmt:message key="settings.not_uniq_new_password"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group mb-3">
                        <label class="form-label"><fmt:message key="profile.repeat_new_password"/></label>
                        <input type="password" name="repeat_password" class="form-control" required pattern="^[A-Za-zА-Яа-я0-9\.]{5,40}$">
                        <div id="passHelp" class="form-text"><fmt:message key="registration.correct_password"></fmt:message></div>
                        <c:if test="${!empty invalid_repeat_password}">
                            <div class="invalid-feedback-backend" style="color: red">
                                <fmt:message key="profile.invalid_repeat_password"/>
                            </div>
                        </c:if>
                        <div class="invalid-feedback">
                            <fmt:message key="profile.invalid_repeat_password"/>
                        </div>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success"><fmt:message key="profile.change_password"/></button>
                    </div>
                </dl>
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
