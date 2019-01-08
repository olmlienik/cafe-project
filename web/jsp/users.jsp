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
    <link href="/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
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
                <h3>Users</h3>
            </div>
            <div class="modal-body">
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#activeUsers" data-toggle="tab">Active Users</a></li>
                        <li><a href="#bannedUsers" data-toggle="tab">Banned Users</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="activeUsers">
                            <table class="table">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">№</th>
                                    <th scope="col">Login</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Loyalty points</th>
                                    <th scope="col">Balance</th>
                                    <th scope="col">Role</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="curUser" items="${requestScope.activeUsers}" varStatus="loop">
                                    <tr>
                                        <th scope="row">${loop.index}</th>
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
                                                            Ban user
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
                                    <th scope="col">Login</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Loyalty points</th>
                                    <th scope="col">Balance</th>
                                    <th scope="col">Role</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="curUser" items="${requestScope.bannedUsers}" varStatus="loop">
                                    <tr>
                                        <th scope="row">${loop.index}</th>
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
                                                                class="btn btn-primary">Unban user
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
