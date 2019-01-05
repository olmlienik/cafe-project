<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 09.12.2018
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link href="/css/registration.css" rel="stylesheet">
</head>

<body>
<c:set var="lastPage" value="/jsp/registrationForm.jsp" scope="session"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>

<c:import url="header.jsp"/>

<div class="login-page">
    <div class="form">
        <form method="post" action="/controller">
            <input type="hidden" name="command" value="sign_up">
            <div class="field required">
                <input type="text" class="login" name="login"
                       placeholder="<fmt:message key="login"/>"
                       pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,25}$"
                       maxlength="25"
                       title="<fmt:message key="login.requirements"/>"
                       required/>
            </div>
            <div class="field required">
                <input type="password" id="password" class="password" name="password"
                       placeholder="<fmt:message key="password"/>"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,25}$"
                       maxlength="25"
                       title="<fmt:message key="password.requirements"/>"
                       required/>
            </div>
            <div class="field required">
                <input type="password" id="confirm-password" class="confirm-password" name="confirm-password"
                       placeholder="<fmt:message key="confirm.password"/>"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,25}$"
                       maxlength="25"
                       required/>
            </div>

            <div class="field required email-container">
                <input type="email" class="email" name="email"
                       placeholder="<fmt:message key="email"/>"
                       pattern="[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+{,25}"
                       maxlength="25"
                       title="<fmt:message key="email.requirements"/>"
                       required/>
            </div>
            <div class="form-group">
                <c:if test="${not empty requestScope.notEqualsPassword}">
                    <p class="text-danger"><fmt:message key="not.equals.password.msg"/></p>
                </c:if>
                <c:if test="${not empty requestScope.loginUsed}">
                    <p class="text-danger"><fmt:message key="login.used.msg"/></p>
                </c:if>
            </div>
            <button type="submit" name="sign_up" value="sign_up"><fmt:message key="sign.up.btn"/></button>
            <p class="message"><a href="../index.jsp"><fmt:message key="back.to.main"/></a></p>
        </form>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>