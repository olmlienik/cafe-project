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
                            <h4>Your Balance : ${sessionScope.user.balance}</h4>
                            <h4>Loyalty points : ${sessionScope.user.loyaltyPoints}</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form  method="post" action="/controller">
                                <input type="hidden" name="command" value="replenish_balance">
                                <div class="form-group row">
                                    <h4>Replenish balance</h4>
                                    <label for="replenishment" class="col-4 col-form-label">Sum to add</label>
                                    <div class="col-8">
                                        <input id="replenishment" name="replenishment" placeholder="Sum to add"
                                               pattern="^[0-9]+?(.)[0-9]*{,25}$"
                                               maxlength="25"
                                               class="form-control here" required="required" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <button name="submit" type="submit" class="btn btn-primary">Replenish balance
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="footer.jsp"/>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
