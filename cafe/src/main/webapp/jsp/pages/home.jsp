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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
    <title>Title</title>
</head>
<body>
<div class="page">
    <header>
        <%@include file="header/header.jsp"%>
    </header>

<div class="container-fluid justify-content-center">
    <p class="text-center"><b>«GoodCafe» – РЕСТОРАН С БЕСПЛАТНОЙ ДОСТАВКОЙ НА ДОМ</b></p>
    <p>Если вы являетесь ценителем вкусной кухни, то не откажите себе в удовольствии и заказать вкуснейшую еду в Минске.
        На нашем сайте вы найдете широкий ассортимент блюд на любой вкус. Все они сделаны из свежих продуктов и натуральных ингредиентов.
        Над приготовлением работают настоящие мастера своего дела. Наши блюда - это оптимальный вариант правильного питания.
    </p>
    <p>Различных рецептов блюд очень много и они постоянно обновляются. Шеф-повары «GoodCafe» постоянно работают над доработкой и улучшением меню,
        обновляя и добавляя новые оригинальные блюда.
    </p>
    <p>На нашем сайте вы сможете купить блюда, которые вы больше всего любите.</p>
    <p class="text-center"><b>КУХНЯ НА ДОМ ОТ GoodCafe</b></p>
    <p>Заказать еду в Минске с доставкой по городу, а также за пределы МКАД, можно с помощью специальной формы на нашем сайте или по указанным телефонам.
        Оставить заявку по телефону можно с 10:30 до 22:30, на сайте круглосуточно (обработка заказа осуществляется с 10:30 до 22:30). Ориентировочное время доставки 1 ч. - 1 ч.20 мин.
        В час пик, выходные и праздничные дни, а также за пределы МКАД срок доставки суши может увеличиться. Время доставки с Вами согласует диспетчер службы.
    </p>

    <p>
        Мы готовим лучшие блюда в Минске для каждого покупателя и поэтому качество наших продуктов всегда на высшем уровне. Вы можете убедится в этом сами, заказав большинство продуктов
        в специальном разделе сайта.. Если вы хотите хорошо провести свое время и при этом еще вкусно поесть, то наша компания «GoodCafe» вам в этом поможет.
        Сделайте заказ наших блюд хотя бы один раз, и вы точно захотите еще - это уже проверено.
    </p>
    <p>
        Приятного аппетита!
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