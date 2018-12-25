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
<fmt:setBundle basename="localisation.locale"/>
<div id="header">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href=""><fmt:message key="cafe"/></a>
            </div>
            <ul class="nav navbar-nav navbar-left">
                <c:choose>
                    <c:when test="${sessionScope.role == 'GUEST'}">

                        <%--<c:if test="${sessionScope.role == 'GUEST'}">--%>
                        <li><a href="controller?command=find_all_dishes"><fmt:message key="menu.btn"/></a></li>


                        <li><a href="../jsp/loginForm.jsp"><fmt:message key="login.btn"/></a></li>

                        <li><a href="../jsp/registrationForm.jsp"><fmt:message key="sign.up"/></a></li>
                        <%--</c:if>--%>

                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${sessionScope.role =='SIGNED_USER'}">

                                <li><a href="../jsp/profileUser.jsp">My profile</a></li>

                            </c:when>
                            <c:when test="${sessionScope.role =='ADMIN'}">
                                <li><a href="../jsp/profileAdmin.jsp">Admin profile</a></li>
                                <%--<li> <button input type="submit" onclick="setCommVal('MAIN_FORWARD')" name="action"--%>
                                <%--value="controlFoodMenu" class="btn btn-default navbar-btn">${menuManage}</button> </li>--%>
                            </c:when>
                        </c:choose>
                        <li><a href="controller?command=logout">Log out</a></li>
                        <%--<li> <button input type="submit" onclick="setCommVal('LOGOUT')" name="action"--%>
                        <%--value="logout" class="btn btn-default navbar-btn">${logout}</button> </li>--%>
                    </c:otherwise>
                </c:choose>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="controller" method="get">
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
