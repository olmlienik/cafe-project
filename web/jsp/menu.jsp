<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 16.12.2018
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <title>Menu</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/controller?command=find_all_dishes" scope="session"/>
<c:import url="header.jsp"/>
<div class="container">
    <div class="page-header">
        <h1>
            <fmt:message key="menu.btn"/>
        </h1>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#snacks" data-toggle="tab">
            <fmt:message key="snacks.title"/></a></li>
        <li><a href="#mainDishes" data-toggle="tab"><fmt:message key="main.dishes.title"/></a></li>
        <li><a href="#deserts" data-toggle="tab"><fmt:message key="deserts.title"/></a></li>
        <li><a href="#drinks" data-toggle="tab"><fmt:message key="drinks.title"/></a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane active in" id="snacks">
            <div class="tab-content">
                <div class="container">
                    <div class="tab-pane fade in active">
                        <c:forEach var="curDish" items="${requestScope.snacks}">
                            <hr>
                            <div class="media">
                                <div class="media-left">
                                    <img src="${curDish.picture}" class="media-object" style="width:300px">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${curDish.name}</h4>
                                    <p><strong><fmt:message key="price"/></strong>${curDish.cost}$ </p>
                                </div>
                            </div>
                            <c:if test="${sessionScope.role =='SIGNED_USER'}">
                                <form method="post" action="/controller">
                                    <input type="hidden" name="currentDishId" value="${curDish.id}"/>
                                    <input type="hidden" name="command" value="add_to_basket">
                                    <button name="submit" type="submit" class="btn btn-primary">
                                        <fmt:message key="add.to.basket"/>
                                    </button>
                                </form>
                            </c:if>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="mainDishes">
            <div class="tab-content">
                <div class="container">
                    <div class="tab-pane fade in active">
                        <c:forEach var="curDish" items="${requestScope.mainDishes}">
                            <hr>
                            <div class="media">
                                <div class="media-left">
                                    <img src="${curDish.picture}" class="media-object" style="width:300px">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${curDish.name}</h4>
                                    <p><strong><fmt:message key="price"/></strong>${curDish.cost}$ </p>
                                </div>
                            </div>
                            <c:if test="${sessionScope.role =='SIGNED_USER'}">
                                <form method="post" action="/controller">
                                    <input type="hidden" name="currentDishId" value="${curDish.id}"/>
                                    <input type="hidden" name="command" value="add_to_basket">
                                    <button name="submit" type="submit" class="btn btn-primary">
                                        <fmt:message key="add.to.basket"/>
                                    </button>
                                </form>
                            </c:if>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="deserts">
            <div class="tab-content">
                <div class="container">
                    <div class="tab-pane fade in active">
                        <c:forEach var="curDish" items="${requestScope.deserts}">
                            <hr>
                            <div class="media">
                                <div class="media-left">
                                    <img src="${curDish.picture}" class="media-object" style="width:300px">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${curDish.name}</h4>
                                    <p><strong><fmt:message key="price"/></strong>${curDish.cost}$ </p>
                                </div>
                            </div>
                            <c:if test="${sessionScope.role =='SIGNED_USER'}">
                                <form method="post" action="/controller">
                                    <input type="hidden" name="currentDishId" value="${curDish.id}"/>
                                    <input type="hidden" name="command" value="add_to_basket">
                                    <button name="submit" type="submit" class="btn btn-primary">
                                        <fmt:message key="add.to.basket"/>
                                    </button>
                                </form>
                            </c:if>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="drinks">
            <div class="tab-content">
                <div class="container">
                    <div class="tab-pane fade in active">
                        <c:forEach var="curDish" items="${requestScope.drinks}">
                            <hr>
                            <div class="media">
                                <div class="media-left">
                                    <img src="${curDish.picture}" class="media-object" style="width:300px">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${curDish.name}</h4>
                                    <p><strong><fmt:message key="price"/></strong>${curDish.cost}$ </p>
                                </div>
                            </div>
                            <c:if test="${sessionScope.role =='SIGNED_USER'}">
                                <form method="post" action="/controller">
                                    <input type="hidden" name="currentDishId" value="${curDish.id}"/>
                                    <input type="hidden" name="command" value="add_to_basket">
                                    <button name="submit" type="submit" class="btn btn-primary">
                                        <fmt:message key="add.to.basket"/>
                                    </button>
                                </form>
                            </c:if>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
