<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 25.12.2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<footer>
    <div class="footer-copyright text-center py-3">
        <p class="text-muted"><fmt:message key="footer.msg"/></p>
    </div>
</footer>
</body>
</html>
