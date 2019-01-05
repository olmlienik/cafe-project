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

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Cafe</title>
    <link href="/css/default.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/fonts.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
</head>

<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/main.jsp" scope="session" />

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
                    <h2>Etiam rhoncus volutpat</h2>
                    <span class="byline">Integer sit amet pede vel arcu aliquet pretium</span></div>
                <p>Integer nisl risus, sagittis convallis, rutrum id, elementum congue, nibh. Suspendisse dictum porta
                    lectus. Donec placerat odio vel elit. Nullam ante orci, pellentesque eget, tempus quis, ultrices in,
                    est. Sed vel tellus. Curabitur sem urna, consequat vel, suscipit in, mattis placerat, nulla. Sed ac
                    leo. Pellentesque imperdiet. Pellentesque viverra vulputate enim.</p>
            </div>
            <div class="ebox2"><span class="fa fa-comments-o"></span>
                <div class="title">
                    <h2>Mauris vulputate dolor</h2>
                    <span class="byline">Integer sit amet pede vel arcu aliquet pretium</span></div>
                <p>Proin gravida orci porttitor enim accumsan lacinia. Donec condimentum, urna non molestie semper,
                    ligula enim ornare nibh, quis laoreet eros quam eget ante. Aliquam libero. Vivamus nisl nibh,
                    iaculis vitae, viverra sit amet, ullamcorper vitae, turpis. Aliquam erat volutpat. Vestibulum dui
                    sem, pulvinar sed, imperdiet nec, iaculis nec, leo. </p>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>

