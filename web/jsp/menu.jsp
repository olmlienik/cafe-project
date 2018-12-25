<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 16.12.2018
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<section class="section-movies">
    <c:forEach var="dish" items="${requestScope.dish}">
        <ul>
            <li>
                <div class="dish">
                    <%--<a href="controller?command=show_movie_page&movieId=${movie.id}">--%>
                        <h4 class="title"><c:out value="${dish.name}"/></h4>
                    <%--</a>--%>

                    <div class="poster">
                        <a href="#">
                            <img src="${dish.picture}" alt="${dish.name}"/>
                        </a>
                    </div>

                    <p class="description"><c:out value="${dish.cost}"/></p>
                </div>
            </li>
        </ul>
    </c:forEach>
    <%--<a href="${requestScope.previous_page}"><fmt:message key="back"/></a>--%>
</section>
</section>
</body>
</html>
