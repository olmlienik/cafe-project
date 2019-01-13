<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 15.12.2018
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Cafe</title>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/default.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/fonts.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/main.jsp" scope="session"/>
<div id="header-featured">
    <div id="banner-wrapper">
        <div id="banner" class="container">
            <h2><fmt:message key="welcome.msg"/></h2>
            <p><fmt:message key="cafe.info"/></p>
        </div>
    </div>
</div>
<div id="wrapper">
    <div id="featured-wrapper">
        <div id="featured" class="extra2 margin-btm container">
            <div class="main-title">
                <h2><fmt:message key="advantages.title"/></h2>
            </div>
            <div class="ebox1"><span class="fa fa-pagelines"></span>
                <div class="title">
                    <h2><fmt:message key="products.quality.title"/></h2>
                    <span class="byline"><fmt:message key="products.quality.msg"/></span></div>
            </div>
            <div class="ebox2"><span class="fa fa-anchor"></span>
                <div class="title">
                    <h2><fmt:message key="staff.title"/></h2>
                    <span class="byline"><fmt:message key="staff.msg"/></span></div>
            </div>
        </div>
        <div class="extra2 container">
            <div class="ebox1"><span class="fa fa-puzzle-piece"></span>
                <div class="title">
                    <h2><fmt:message key="cosy.atmosphere.title"/></h2>
                    <span class="byline"><fmt:message key="cosy.atmosphere.msg"/></span></div>
            </div>
            <div class="ebox2"><span class="fa fa-comments-o"></span>
                <div class="title">
                    <h2><fmt:message key="board.games.title"/></h2>
                    <span class="byline"><fmt:message key="board.games.msg"/></span></div>
            </div>
        </div>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>

