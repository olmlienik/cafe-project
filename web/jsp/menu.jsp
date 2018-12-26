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
<html>
<head>
    <title>Menu</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/controller?command=find_all_dishes" scope="session" />

<c:import url="header.jsp"/>

<div class="tab-content">
    <div class="container">
        <div class="tab-pane fade in active">
            <c:forEach var="dish" items="${requestScope.dish}">
                <div class="media">
                    <div class="media-left">
                        <img src="${dish.picture}" class="media-object" style="width:300px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${dish.name}</h4>
                            <%--<p>${dish.descr}</p>--%>
                        <p><strong>Price</strong>${dish.cost}$ </p>
                    </div>
                </div>
                <button type="submit" name="foodElem" value="meal|${meal.id}" class="btn btn-default">Add to order</button>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>

<c:import url="footer.jsp"/>
</body>
</html>
