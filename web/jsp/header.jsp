<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 19.12.2018
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<div id="header">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                    <fmt:message key="cafe"/></a>
            </div>
            <ul class="nav navbar-nav navbar-left">
                <c:choose>
                    <c:when test="${sessionScope.role == 'GUEST'}">
                        <li><a href="/controller?command=find_all_dishes"><fmt:message key="menu.btn"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/jsp/loginForm.jsp">
                            <fmt:message key="login.btn"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/jsp/registrationForm.jsp">
                            <fmt:message key="sign.up"/></a></li>
                        <li><a href="/controller?command=find_all_reviews"><fmt:message key="reviews.btn"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${sessionScope.role =='SIGNED_USER'}">
                                <li><a href="/controller?command=find_all_dishes"><fmt:message key="menu.btn"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/jsp/basket.jsp">
                                    <fmt:message key="basket.btn"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/jsp/profileUser.jsp">
                                    <fmt:message key="my.profile.btn"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/jsp/balance.jsp">
                                    <fmt:message key="balance.btn"/></a></li>
                            </c:when>
                            <c:when test="${sessionScope.role =='ADMIN'}">
                                <li><a href="${pageContext.request.contextPath}/jsp/profileUser.jsp">
                                    <fmt:message key="my.profile.btn"/></a></li>
                                <li><a href="/controller?command=find_orders_in_process">
                                    <fmt:message key="orders.btn"/></a></li>
                                <li><a href="/controller?command=find_all_users">
                                    <fmt:message key="users.btn"/></a></li>
                                <li><a href="/controller?command=find_all_dishes">
                                    <fmt:message key="menu.btn"/></a></li>
                            </c:when>
                        </c:choose>
                        <li><a href="/controller?command=find_all_reviews"><fmt:message key="reviews.btn"/></a></li>
                        <li><a href="/controller?command=logout"><fmt:message key="logout"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="/controller" method="get">
                        <input type="hidden" name="command" value="change_language">
                        <button type="submit" name="language"
                                value="en_US" class="btn btn-default navbar-btn">EN
                        </button>
                        <button type="submit" name="language"
                                value="rus_RU" class="btn btn-default navbar-btn">RU
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>
