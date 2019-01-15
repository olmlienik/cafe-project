<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 20.12.2018
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cafe" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <title>Reviews</title>
    <link href="${pageContext.request.contextPath}/css/review.css" rel="stylesheet" media='all'>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.locale"/>
<c:import url="/jsp/header.jsp"/>
<c:set var="lastPage" value="/controller?command=find_all_reviews" scope="session"/>
<h2 align="center"><fmt:message key="reviews.title"/></h2>
<c:if test="${sessionScope.role =='SIGNED_USER'}">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h3><fmt:message key="add.review.title"/></h3>
                        <div class="row">
                            <div class="col-md-12">
                                <form method="post" action="/controller">
                                    <input type="hidden" name="command" value="add_review">
                                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                    <div class="form-group row">
                                        <label for="body" class="col-4 col-form-label">
                                            <fmt:message key="body.label"/></label>
                                        <div class="col-8">
                                            <input id="body" name="body"
                                                   placeholder="<fmt:message key="add.review.title"/>"
                                                   minlength="4"
                                                   maxlength="256"
                                                   title="<fmt:message key="body.requirements.msg"/>"
                                                   class="form-control here" required="required" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="offset-4 col-8">
                                            <button name="submit" type="submit" class="btn btn-primary">
                                                <fmt:message key="add.review.btn"/>
                                            </button>
                                        </div>
                                    </div>
                                    <c:if test="${not empty requestScope.invalidBody}">
                                        <p class="text-danger"><fmt:message key="body.requirements.msg"/></p>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="container" align="center">
    <img class="line_book" src="/images/line-pero.png">
    <div class="reviews_items">
        <c:forEach var="curReview" items="${requestScope.reviews}">
            <div class="reviews_item">
                <div class="w-row">
                    <div class="w-col w-col-2">
                        <div class="reviews_name">${curReview.login}</div>
                        <p>${curReview.date}</p>
                    </div>
                    <div class="w-col w-col-10">
                        <div class="main_reviews_ramka single_review_item">
                            <p class="main_reviews_text">${curReview.body}</p>
                            <img class="w-hidden-small w-hidden-tiny arrow_left" src="/images/arrow-left.png">
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${sessionScope.role =='ADMIN'}">
                <form method="post" action="/controller">
                    <input type="hidden" name="command" value="delete_review">
                    <input type="hidden" name="currentReviewId" value="${curReview.id}">
                    <button name="submit" type="submit" class="btn btn-primary">
                        <fmt:message key="delete.review.btn"/></button>
                </form>
            </c:if>
        </c:forEach>
    </div>
</div>
<cafe:customFooter locale="${sessionScope.locale}"/>
</body>
</html>
