<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 19.12.2018
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld"%>
<html>
<head>
    <title>Admin profile</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/profileAdmin.jsp" scope="session" />
<c:import url="header.jsp"/>
Admin Profile HELLO
<cafe:customFooter locale="${sessionScope.locale}" />
</body>
</html>
