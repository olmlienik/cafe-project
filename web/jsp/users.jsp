<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 05.01.2019
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <title>Users</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/controller?command=find_all_users" scope="session"/>
<div class="container">
    <div class="row">
        <div class="span12">
            <div class="modal-header">
                <h3><fmt:message key="users.title"/></h3>
            </div>
            <div class="modal-body">
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#activeUsers" data-toggle="tab">
                            <fmt:message key="active.users"/></a></li>
                        <li><a href="#bannedUsers" data-toggle="tab"><fmt:message key="banned.users"/></a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="activeUsers">
                            <table class="table">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">№</th>
                                    <th scope="col">ID</th>
                                    <th scope="col"><fmt:message key="login.col"/></th>
                                    <th scope="col"><fmt:message key="email.col"/></th>
                                    <th scope="col"><fmt:message key="points.col"/></th>
                                    <th scope="col"><fmt:message key="balance.col"/></th>
                                    <th scope="col"><fmt:message key="role.col"/></th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="curUser" items="${requestScope.activeUsers}" varStatus="loop">
                                    <tr>
                                        <th scope="row">${loop.index}</th>
                                        <td>${curUser.id}</td>
                                        <td>${curUser.login}</td>
                                        <td>${curUser.email}</td>
                                        <td>${curUser.loyaltyPoints}</td>
                                        <td>${curUser.balance}</td>
                                        <td>${curUser.role}</td>
                                        <td>
                                            <form method="post" action="/controller">
                                                <input type="hidden" name="userId" value="${curUser.id}"/>
                                                <input type="hidden" name="command" value="ban_user">
                                                <div class="form-group row">
                                                    <div class="offset-4 col-8">
                                                        <button name="submit" type="submit" class="btn btn-primary">
                                                            <fmt:message key="ban.btn"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="bannedUsers">
                            <table class="table">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">№</th>
                                    <th scope="col">ID</th>
                                    <th scope="col"><fmt:message key="login.col"/></th>
                                    <th scope="col"><fmt:message key="email.col"/></th>
                                    <th scope="col"><fmt:message key="points.col"/></th>
                                    <th scope="col"><fmt:message key="balance.col"/></th>
                                    <th scope="col"><fmt:message key="role.col"/></th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="curUser" items="${requestScope.bannedUsers}" varStatus="loop">
                                    <tr>
                                        <th scope="row">${loop.index}</th>
                                        <td>${curUser.id}</td>
                                        <td>${curUser.login}</td>
                                        <td>${curUser.email}</td>
                                        <td>${curUser.loyaltyPoints}</td>
                                        <td>${curUser.balance}</td>
                                        <td>${curUser.role}</td>
                                        <td>
                                            <form method="post" action="/controller">
                                                <input type="hidden" name="userId" value="${curUser.id}"/>
                                                <input type="hidden" name="command" value="unban_user">
                                                <div class="form-group row">
                                                    <div class="offset-4 col-8">
                                                        <button name="submit" type="submit"
                                                                class="btn btn-primary"><fmt:message key="unban.btn"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
