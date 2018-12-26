<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link href="/css/registration.css" rel="stylesheet">

</head>

<body>

<c:set var="lastPage" value="../jsp/loginForm.jsp" scope="session" />
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>


<c:import url="header.jsp"/>

<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="/controller">
            <input type="hidden" name="command" value="login">
            <div class="field required">
                <input type="text" class="login" name="login"
                       placeholder="<fmt:message key="username"/>"
                       pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,45}$"
                       title="<fmt:message key="login.requirements"/>"
                       required/>
            </div>
            <div class="field required">
                <input type="password" id="password" class="password" name="password"
                       placeholder="<fmt:message key="password"/>"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,45}$"
                       title="<fmt:message key="password.requirements"/>"
                       required/>
            </div>
            <button type="submit" value="login"><fmt:message key="login.btn"/></button>
            <c:if test="${not empty wrongLoginOrPassword}">
                <p class="text-danger"><fmt:message key="wrong.login.password"/></p>
            </c:if>
            <p class="message"><fmt:message key="not.registered.msg"/><a href="../jsp/registrationForm.jsp"><fmt:message
                    key="create.account.msg"/></a></p>
            <p class="message"><a href="../index.jsp"><fmt:message key="back.to.main"/></a></p>
        </form>
    </div>
</div>

<c:import url="footer.jsp"/>

<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>