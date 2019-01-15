<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/loginForm.jsp" scope="session"/>
<c:import url="header.jsp"/>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="/controller">
            <input type="hidden" name="command" value="login">
            <div class="field required">
                <input type="text" class="login" name="login"
                       placeholder="<fmt:message key="username"/>"
                       pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,25}$"
                       title="<fmt:message key="login.requirements"/>"
                       required/>
            </div>
            <div class="field required">
                <input type="password" id="password" class="password" name="password"
                       placeholder="<fmt:message key="password"/>"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,25}$"
                       title="<fmt:message key="password.requirements"/>"
                       required/>
            </div>
            <button type="submit" value="login"><fmt:message key="login.btn"/></button>
            <c:if test="${not empty requestScope.wrongLoginOrPassword}">
                <p class="text-danger"><fmt:message key="wrong.login.password"/></p>
            </c:if>
            <c:if test="${not empty requestScope.isBanned}">
                <p class="text-danger"><fmt:message key="banned.msg"/></p>
            </c:if>
            <p class="message"><fmt:message key="not.registered.msg"/>
                <a href="${pageContext.request.contextPath}/jsp/registrationForm.jsp">
                    <fmt:message key="create.account.msg"/></a></p>
            <p class="message"><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="back.to.main"/></a></p>
        </form>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>