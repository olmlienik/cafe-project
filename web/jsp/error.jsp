<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 31.12.2018
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <title>Error</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/error.jsp" scope="session" />
<c:import url="header.jsp"/>
<h1><fmt:message key="error.msg"/></h1>
<cafe:customFooter locale="${sessionScope.locale}" />
</body>
</html>
