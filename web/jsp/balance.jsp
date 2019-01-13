<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 04.01.2019
  Time: 15:50
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
    <title>Balance</title>
</head>

<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:set var="lastPage" value="/jsp/balance.jsp" scope="session"/>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4><fmt:message key="balance.title"/> ${sessionScope.user.balance}</h4>
                            <h4><fmt:message key="loyalty.points.title"/> ${sessionScope.user.loyaltyPoints}</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form method="post" action="/controller">
                                <input type="hidden" name="command" value="replenish_balance">
                                <div class="form-group row">
                                    <h4><fmt:message key="replenish.balance.btn"/></h4>
                                    <label for="replenishment" class="col-4 col-form-label">
                                        <fmt:message key="sum.to.add"/></label>
                                    <div class="col-8">
                                        <input id="replenishment" name="replenishment" placeholder="Sum to add"
                                               pattern="([\d]{1,3}((\.)([\d]){1,2})?)"
                                               title="<fmt:message key="sum.requirements.msg"/>"
                                               class="form-control here" required="required" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <button name="submit" type="submit" class="btn btn-primary">
                                            <fmt:message key="replenish.balance.btn"/>
                                        </button>
                                    </div>
                                </div>
                                <c:if test="${not empty requestScope.wrongBalance}">
                                    <p class="text-danger"><fmt:message key="wrong.balance.msg"/></p>
                                </c:if>
                            </form>
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
