<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 31.12.2018
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld"%>
<html>
<head>
    <title>My basket</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/basket.jsp" scope="session"/>
<c:import url="header.jsp"/>

<div class="tab-content">
    <div class="container">
        <div class="tab-pane fade in active">
            <c:set var="basket" value="${sessionScope.basket.dishes}"/>
            <c:forEach var="dish" items="${basket}" varStatus="loop">
                <div class="media">
                    <div class="media-left">
                        <img src="${dish.picture}" class="media-object" style="width:300px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${dish.name}</h4>
                        <h4><strong><fmt:message key="price"/></strong>${dish.cost}$ </h4>
                    </div>
                </div>
                <h3><a href="/controller?command=remove_from_basket&dishIndex=${loop.index}">
                       <fmt:message key="remove.from.basket.msg"/></a></h3>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="tab-content">
        <div class="container">
            <h2><fmt:message key="total.cost.label"/>${sessionScope.basket.price}</h2>
            <form class="create-order" method="post" action="/controller">
                <input type="hidden" name="command" value="create_order">
                <label for="time" class="control-label"><fmt:message key="choose.delivery.time.label"/></label>
                <input id="time" type="time" name="deliveryTime" min="9:00" max="23:00"
                       value="${requestScope.deliveryTime}" required>
                <label  for="time" class="control-label"><fmt:message key="work.time.msg"/></label><br>

                <label class="control-label"><fmt:message key="choose.pay.type.label"/></label>
                <label class="radio-inline">
                    <input type="radio" name="paymentType" value="from_account" checked><fmt:message key="pay.from.account.msg"/>
                </label>
                <%--<label class="radio-inline">--%>
                <%--<input type="radio" name="paymentType" value="bonus">With bonuses--%>
                <%--</label>--%>
                <label class="radio-inline">
                    <input type="radio" name="paymentType" value="when_receiving"><fmt:message key="pay.when.receiving"/>
                </label><br>
                <button type="submit" value="create_order"><fmt:message key="create.order.btn"/></button>
                <c:if test="${not empty requestScope.emptyBasket}">
                    <p class="text-danger"><fmt:message key="empty.basket.msg"/></p>
                </c:if>
                <c:if test="${not empty requestScope.badPayType}">
                    <p class="text-danger"><fmt:message key="bad.pay.type.msg"/></p>
                </c:if>
                <c:if test="${not empty requestScope.badTime}">
                    <p class="text-danger"><fmt:message key="bad.time.msg"/></p>
                </c:if>
                <c:if test="${not empty requestScope.notEnough}">
                    <p class="text-danger"><fmt:message key="not.enough.msg"/></p>
                </c:if>
            </form>
            <br>
            <form class="cancel-order" method="post" action="/controller">
                <input type="hidden" name="command" value="cancel_order">
                <button type="submit" value="cancel_order"><fmt:message key="cancel.order.btn"/></button>
            </form>
        </div>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}" />
</body>
</html>
