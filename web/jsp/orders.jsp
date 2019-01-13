<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 05.01.2019
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>Current orders</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/controller?command=find_orders_in_process" scope="session"/>
<div class="container">
    <h3><fmt:message key="orders.title"/></h3>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">№</th>
            <th scope="col"><fmt:message key="client.id.col"/></th>
            <th scope="col"><fmt:message key="getting.time.col"/></th>
            <th scope="col"><fmt:message key="payment.type.col"/></th>
            <th scope="col"><fmt:message key="cost.col"/></th>
            <th scope="col"><fmt:message key="composition.col"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty requestScope.noOrdersInProcess}">
            <h3><fmt:message key="orders.finished.msg"/></h3>
        </c:if>
        <c:forEach var="curOrder" items="${requestScope.ordersInProcess}" varStatus="loop">
            <tr>
                <th scope="row">${loop.index + 1}</th>
                <td>${curOrder.idUser}</td>
                <td>${curOrder.deliveryTime}</td>
                <td>${curOrder.paymentType}</td>
                <td>${curOrder.price}</td>
                <td>
                    <c:forEach var="curDish" items="${curOrder.dishes}" varStatus="loop">
                        ${curDish.name}<br>
                    </c:forEach>
                </td>
                <td>
                    <form method="post" action="/controller">
                        <input type="hidden" name="orderId" value="${curOrder.id}"/>
                        <input type="hidden" name="clientId" value="${curOrder.idUser}"/>
                        <input type="hidden" name="command" value="approve_order">
                        <div class="form-group row">
                            <div class="offset-4 col-8">
                                <button name="submit" type="submit" class="btn btn-primary">
                                    <fmt:message key="approve.btn"/></button>
                            </div>
                        </div>
                    </form>
                </td>
                <td>
                    <form method="post" action="/controller">
                        <input type="hidden" name="orderId" value="${curOrder.id}"/>
                        <input type="hidden" name="clientId" value="${curOrder.idUser}"/>
                        <input type="hidden" name="command" value="reject_order">
                        <div class="form-group row">
                            <div class="offset-4 col-8">
                                <button name="submit" type="submit" class="btn btn-primary">
                                    <fmt:message key="missed.btn"/></button>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
