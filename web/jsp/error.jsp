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
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <title>Error</title>
</head>
<body>
<c:set var="lastPage" value="/jsp/error.jsp" scope="session" />
<c:import url="header.jsp"/>
<h1>Ooops, something happened!</h1>
<c:import url="footer.jsp"/>
</body>
</html>
