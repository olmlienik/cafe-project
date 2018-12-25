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
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link href="/css/registration.css" rel="stylesheet">
</head>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localisation.locale"/>
<div class="login-page">
    <div class="form">
        <%--class="registration-form"--%>
        <form  method="post" action="/controller">
            <input type="hidden" name="command" value="sign_up">
            <div class="field required">
                <input type="text" class="login" name="login"
                       placeholder="<fmt:message key="username"/>"
                       pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,}$"
                       title="<fmt:message key="login.requirements"/>"
                       required/>
            </div>
            <div class="field required">
                <input type="password" id="password" class="password" name="password"
                       placeholder="<fmt:message key="password"/>"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$"
                       title="<fmt:message key="password.requirements"/>"
                       required/>
            </div>
            <div class="field required">
                <input type="password" id="confirm-password" class="confirm-password" name="confirm-password"
                       placeholder="<fmt:message key="confirm.password"/>"
                       required/>
            </div>

            <div class="field required email-container">
                <input type="email" class="email" name="email"
                       placeholder="<fmt:message key="email"/>"
                       pattern="[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+"
                       title="<fmt:message key="email.requirements"/>"
                       required/>
            </div>

            <div class="field optional">
                <input type="text" class="first-name" name="first-name"
                       placeholder="<fmt:message key="first.name"/>">
            </div>

            <div class="field optional">
                <input type="text" class="last-name" name="last-name"
                       placeholder="<fmt:message key="last.name"/>">
            </div>
            <div class="form-group">
                <c:if test = "${not empty notEqualsPassword}">
                    <p class="text-danger"><fmt:message key="not.equals.password.msg"/></p>
                </c:if>
                <c:if test = "${not empty loginUsed}">
                    <p class="text-danger"><fmt:message key="login.used.msg"/></p>
                </c:if>
            </div>
            <button type="submit" name="sign_up" value="sign_up"><fmt:message key="sign.up.btn"/></button>
            <p class="message"><a href="../index.jsp"><fmt:message key="back.to.main"/></a></p>
        </form>
    </div>
</div>
</body>
</html>