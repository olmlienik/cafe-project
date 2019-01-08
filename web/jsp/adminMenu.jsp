<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 06.01.2019
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>Menu</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/adminMenu.jsp" scope="session"/>
<c:import url="header.jsp"/>

<div class="container">
    <div class="row">
        <div class="span12">
            <div class="modal-header">
                <h3>Menu management</h3>
            </div>
            <div class="modal-body">
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#addDish" data-toggle="tab">Add new dish</a></li>
                        <li><a href="#currentMenu" data-toggle="tab">Current menu</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="addDish">
                            <div class="container">
                                <div class="col-md-9">
                                    <div class="card">
                                        <div class="card-body">
                                            <%--<div class="row">--%>
                                                <%--<div class="col-md-12">--%>
                                                    <%--<h4>Menu management</h4>--%>
                                                    <%--<hr>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form method="post" action="/controller">
                                                        <input type="hidden" name="command" value="add_new_dish">
                                                        <div class="form-group row">
                                                            <label for="name" class="col-4 col-form-label">Dish
                                                                name</label>
                                                            <div class="col-8">
                                                                <input id="name" name="name" placeholder="Dish name"
                                                                       maxlength="25"
                                                                       class="form-control here" required="required"
                                                                       type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="cost"
                                                                   class="col-4 col-form-label">Cost</label>
                                                            <div class="col-8">
                                                                <input id="cost" name="cost" placeholder="Cost"
                                                                       pattern="^(\d+((\.)?\d+)?)"
                                                                       maxlength="25"
                                                                       class="form-control here" required="required"
                                                                       type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="cost" class="col-4 col-form-label">Picture
                                                                name
                                                                (example :
                                                                picture.jpg)</label>
                                                            <div class="col-8">
                                                                <input id="picture_name" name="picture_name"
                                                                       placeholder="Picture name"
                                                                       maxlength="25"
                                                                       class="form-control here" required="required"
                                                                       type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-4 col-form-label">Category</label>
                                                            <div class="col-8">
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="snack"
                                                                           checked>Snack
                                                                </label><br>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="main">Main
                                                                </label><br>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="desert">Desert
                                                                </label><br>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="drink"
                                                                           checked>Drink
                                                                </label><br>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="offset-4 col-8">
                                                                <button name="submit" type="submit"
                                                                        class="btn btn-primary">Add new dish
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <c:if test="${not empty requestScope.wrongName}">
                                                            <p class="text-danger">Enter the correct name of
                                                                dish</p>
                                                        </c:if>
                                                        <c:if test="${not empty requestScope.wrongCost}">
                                                            <p class="text-danger">Enter the correct cost of dish.
                                                                Must be < 1000.</p>
                                                        </c:if>
                                                        <c:if test="${not empty requestScope.wrongPictureName}">
                                                            <p class="text-danger">Can't find picture with this
                                                                name. Check the name.</p>
                                                        </c:if>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="currentMenu">
                            <form class="login-form" method="post" action="/controller">
                                <input type="hidden" name="command" value="find_all_dishes">
                                <input type="submit" name="find" value="find">
                            </form>
                            sth wil be here
                            <c:forEach var="curDish" items="${requestScope.dish}">
                                <div class="media">
                                    <div class="media-left">
                                        <img src="${curDish.picture}" class="media-object" style="width:100px">
                                    </div>
                                    <div class="media-body">
                                        <h4 class="media-heading">${curDish.name}</h4>
                                        <p><strong><fmt:message key="price"/></strong>${curDish.cost}$ </p>
                                    </div>
                                </div>
                                <%--<c:if test="${sessionScope.role =='SIGNED_USER'}">--%>
                                    <%--<h3> <a href="/controller?command=add_to_basket&currentDishId=${curDish.id}"><fmt:message key="add.to.basket"/></a> </h3>--%>
                                <%--</c:if>--%>
                                <hr>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
