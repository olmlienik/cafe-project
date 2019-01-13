<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 19.12.2018
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <title>My profile</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/profileUser.jsp" scope="session"/>
<div class="container">
    <div class="col-md-9">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <h4><fmt:message key="profile.title"/></h4>
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <form method="post" action="/controller">
                            <input type="hidden" name="command" value="change_username">
                            <div class="form-group row">
                                <label for="username" class="col-4 col-form-label">
                                    <fmt:message key="username.label"/></label>
                                <div class="col-8">
                                    <input id="username" name="username"
                                           placeholder="<fmt:message key="username.label"/>"
                                           value="${sessionScope.user.login}"
                                           pattern="^([a-zA-Z]+)[a-zA-Z\d_]{4,25}$"
                                           maxlength="25"
                                           class="form-control here" required="required" type="text">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="offset-4 col-8">
                                    <button name="submit" type="submit" class="btn btn-primary">
                                        <fmt:message key="change.username"/>
                                    </button>
                                </div>
                            </div>
                            <c:if test="${not empty requestScope.loginUsed}">
                                <p class="text-danger"><fmt:message key="login.used.msg"/></p>
                            </c:if>
                        </form>
                        <form method="post" action="/controller">
                            <input type="hidden" name="command" value="change_password">
                            <div class="form-group row">
                                <label for="old_pass" class="col-4 col-form-label">
                                    <fmt:message key="old.password.label"/></label>
                                <div class="col-8">
                                    <input id="old_pass" name="old_pass"
                                           placeholder="<fmt:message key="old.password.label"/>"
                                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,25}$"
                                           maxlength="25"
                                           class="form-control here" type="password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="new_pass" class="col-4 col-form-label">
                                    <fmt:message key="new.password.label"/></label>
                                <div class="col-8">
                                    <input id="new_pass" name="new_pass"
                                           placeholder="<fmt:message key="new.password.label"/>"
                                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,25}$"
                                           maxlength="25"
                                           class="form-control here" type="password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="offset-4 col-8">
                                    <button name="submit" type="submit" class="btn btn-primary">
                                        <fmt:message key="change.password.label"/>
                                    </button>
                                </div>
                            </div>
                            <c:if test="${not empty requestScope.notEqualsPass}">
                                <p class="text-danger"><fmt:message key="not.equals.password.msg"/></p>
                            </c:if>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
