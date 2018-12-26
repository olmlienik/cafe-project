<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 17.12.2018
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">

    <c:set var="language" value="${sessionScope.languge}"/>
    <fmt:setBundle basename="localization.locale"/>
    <fmt:setLocale value="${language}" />

    <title>Our menu</title>

</head>

<body>
<c:import url="header.jsp"/>
<form action="/controller" method="post">

    <%--<c:set var="lastPage" value="/jsp/base/demoFoodMenu.jsp" scope="session" />--%>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#panel1">meal</a></li>
            <li><a data-toggle="tab" href="#panel2">desserts</a></li>
            <li><a data-toggle="tab" href="#panel3">drinks</a></li>
        </ul>

        <div class="tab-content">
            <div id="panel1" class="tab-pane fade in active">
                <h3>meal</h3>

                <c:forEach var="dish" items="${requestScope.dish}">
                    <ul>
                        <li>
                            <div class="dish">
                                <h4 class="title"><c:out value="${dish.name}"/></h4>

                                <div class="poster">
                                    <a href="#">
                                        <img src="${dish.picture}" alt="${dish.name}"/>
                                    </a>
                                </div>

                                <p class="description"><c:out value="${dish.cost}"/></p>
                            </div>
                        </li>
                    </ul>
                </c:forEach>

            </div>

            <div id="panel2" class="tab-pane fade">
                <div class="container">
                    <h3>${desserts}</h3>

                    <c:forEach var="dessert" items="${dessertArr}" >
                        <div class="media">
                            <div class="media-left">
                                <img src="${dessert.imgPath}" class="media-object" style="width:100px">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">${dessert.name}</h4>
                                <p>${dessert.descr}</p>
                                <p> <strong>${elPrice}</strong>${dessert.price}$ </p>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>

                </div>
            </div>

            <div id="panel3" class="tab-pane fade">
                <h3>${drinks}</h3>

                <c:forEach var="drink" items="${drinkArr}" >
                    <div class="media">
                        <div class="media-left">
                            <img src="${drink.picture}" class="media-object" style="width:100px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${drink.name}</h4>
                            <p>${drink.descr}</p>
                            <p> <strong>${elPrice}</strong>${drink.price}$ </p>
                        </div>
                    </div>
                    <hr>
                </c:forEach>

            </div>
        </div>
    </div>

    <c:import url="footer.jsp"/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

</form>

</body>
</html>
