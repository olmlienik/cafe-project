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
<c:set var="lastPage" value="/controller?command=find_all_dishes" scope="session"/>
<c:import url="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="span12">
            <div class="modal-header">
                <h3><fmt:message key="menu.management.title"/></h3>
            </div>
            <div class="modal-body">
                <div class="well">
                    <h3><fmt:message key="add.dish.title"/></h3>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="addDish">
                            <div class="container">
                                <div class="col-md-9">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form method="post" action="/controller">
                                                        <input type="hidden" name="command" value="add_new_dish">
                                                        <div class="form-group row">
                                                            <label for="name" class="col-4 col-form-label">
                                                                <fmt:message key="dish.name.label"/></label>
                                                            <div class="col-8">
                                                                <input id="name" name="name"
                                                                       placeholder="<fmt:message key="dish.name.label"/>"
                                                                       maxlength="25"
                                                                       class="form-control here" required="required"
                                                                       type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="cost"
                                                                   class="col-4 col-form-label">
                                                                <fmt:message key="cost.label"/></label>
                                                            <div class="col-8">
                                                                <input id="cost" name="cost"
                                                                       placeholder="<fmt:message key="cost.label"/>"
                                                                       pattern="^(\d+((\.)?\d+)?)"
                                                                       maxlength="25"
                                                                       class="form-control here" required="required"
                                                                       type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="cost" class="col-4 col-form-label">
                                                                <fmt:message key="picture.label"/></label>
                                                            <div class="col-8">
                                                                <input id="picture_name" name="picture_name"
                                                                       placeholder="<fmt:message key="picture.title"/>"
                                                                       maxlength="25"
                                                                       class="form-control here" required="required"
                                                                       type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-4 col-form-label">
                                                                <fmt:message key="category.label"/></label>
                                                            <div class="col-8">
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="snack"
                                                                           checked><fmt:message key="snack.title"/>
                                                                </label><br>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="main"><fmt:message key="main.title"/>
                                                                </label><br>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="desert"><fmt:message
                                                                        key="desert.title"/>
                                                                </label><br>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="category"
                                                                           value="drink"
                                                                           checked><fmt:message key="drink.title"/>
                                                                </label><br>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="offset-4 col-8">
                                                                <button name="submit" type="submit"
                                                                        class="btn btn-primary">
                                                                    <fmt:message key="add.dish.btn"/>
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <c:if test="${not empty requestScope.wrongName}">
                                                            <p class="text-danger">
                                                                <fmt:message key="wrong.name.msg"/></p>
                                                        </c:if>
                                                        <c:if test="${not empty requestScope.wrongCost}">
                                                            <p class="text-danger">
                                                                <fmt:message key="wrong.cost.msg"/></p>
                                                        </c:if>
                                                        <c:if test="${not empty requestScope.wrongPictureName}">
                                                            <p class="text-danger">
                                                                <fmt:message key="wrong.picture.msg"/></p>
                                                        </c:if>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-header">
        <h3><fmt:message key="current.menu.title"/></h3>
    </div>
    <div class="tab-content">
        <div class="container">
            <div class="tab-pane fade in active">
                <c:forEach var="curDish" items="${requestScope.dish}">
                    <div class="media">
                        <div class="media-left">
                            <img src="${curDish.picture}" class="media-object" style="width:300px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${curDish.name}</h4>
                            <p><strong><fmt:message key="price"/></strong>${curDish.cost}$ </p>
                        </div>
                    </div>
                    <c:if test="${sessionScope.role =='ADMIN'}">
                        <form method="post" action="/controller">
                            <input type="hidden" name="currentDishId" value="${curDish.id}"/>
                            <input type="hidden" name="command" value="delete_dish">
                            <button name="submit" type="submit" class="btn btn-primary">
                                <fmt:message key="delete.dish.bth"/>
                            </button>
                        </form>
                    </c:if>
                    <hr>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
