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
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld"%>
<html>
<head>
    <title>Menu</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/controller?command=find_all_dishes" scope="session"/>

<c:import url="header.jsp"/>

<div class="tab-content">
    <h2>Menu</h2>
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
                <c:if test="${sessionScope.role =='SIGNED_USER'}">
                <h3> <a href="/controller?command=add_to_basket&currentDishId=${curDish.id}"><fmt:message key="add.to.basket"/></a> </h3>
                </c:if>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>

<cafe:customFooter locale="${sessionScope.locale}" />
</body>
</html>
