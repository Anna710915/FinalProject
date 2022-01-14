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
  <link rel="stylesheet" href="/CSS/styles.css">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script type="text/javascript">
    window.history.forward();
    function noBack() {
      window.history.forward();
    }
  </script>
  <title><fmt:message key="section.title"/> </title>
</head>
<body>
<div class="page">
  <header>
    <%@include file="../header/header.jsp"%>
  </header>

  <div class="container justify-content-center col-12 col-sm-6 mt-3">
    <h3 class="text-center p-3"><fmt:message key="action.add_new_section"/></h3>
    <form name="add_new_section" method="post" action="${absolutePath}/controller" class="needs-validation" novalidate>
      <input type="hidden" name="command" value="insert_new_section">
      <div class="form-group">
        <label class="form-label"><fmt:message key="section.name"/></label>
        <input type="text" name="section_name" class="form-control" required pattern=".{1,20}">
        <c:if test="${! empty invalid_section_name}">
          <div class="invalid-feedback-backend" style="color: red">
            <fmt:message key="${invalid_section_name}"/>
          </div>
        </c:if>
        <div class="invalid-feedback">
          <fmt:message key="section.invalid_name"/>
        </div>
        <c:if test="${! empty not_uniq_section_name}">
          <div class="invalid-feedback-backend" style="color: red">
            <fmt:message key="${not_uniq_section_name}"/>
          </div>
        </c:if>
      </div>
      <div class="text-center mb-3">
        <button type="submit" class="btn btn-primary"><fmt:message key="menu.insert_menu"/></button>
      </div>
    </form>
  </div>
  <div class="text-center">
    <ctg:footertag/>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

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
</body>
</html>