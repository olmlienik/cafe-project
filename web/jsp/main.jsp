<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 15.12.2018
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Cafe</title>
    <link href="/css/default.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/fonts.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
</head>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localisation.locale"/>

<div id="header">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href=""><fmt:message key="cafe"/></a>
            </div>
            <ul class="nav navbar-nav navbar-left">
                <li><a href="controller?command=find_all_dishes"><fmt:message key="menu.btn"/></a></li>

                <li><a href="../jsp/loginForm.jsp"><fmt:message key="login.btn"/></a></li>

                <li><a href="../jsp/registrationForm.jsp"><fmt:message key="sign.up"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="change_language">
                        <button type="submit" name="language"
                                value="en_US" class="btn btn-default navbar-btn">EN
                        </button>

                        <button type="submit" name="language"
                                value="rus_RU" class="btn btn-default navbar-btn">RU
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</div>

<div id="header-featured">
    <div id="banner-wrapper">
        <div id="banner" class="container">
            <h2><fmt:message key="welcome.msg"/></h2>
            <p>Some info about my cafe...</p>
        </div>
    </div>
</div>

<div id="wrapper">
    <div id="featured-wrapper">
        <div id="featured" class="extra2 margin-btm container">
            <div class="main-title">
                <h2>Our advantages</h2>
            </div>

            <div class="ebox1"><span class="fa fa-pagelines"></span>
                <div class="title">
                    <h2>Products quality</h2>
                    <span class="byline">We use only fresh and organic products for our dishes.</span></div>
                <p>Consectetuer adipiscing elit. Nam pede erat, porta eu, lobortis eget, tempus et, tellus. Etiam neque.
                    Vivamus consequat lorem at nisl. Nullam non wisi a sem semper eleifend. Donec mattis libero eget
                    urna. Duis pretium velit ac mauris. Proin eu wisi suscipit nulla suscipit interdum. Aenean lectus
                    lorem, imperdiet at, ultrices eget, ornare et, wisi. </p>
            </div>
            <div class="ebox2"><span class="fa fa-anchor"></span>
                <div class="title">
                    <h2>Donec dictum metus</h2>
                    <span class="byline">Integer sit amet pede vel arcu aliquet pretium</span></div>
                <p>Donec pulvinar ullamcorper metus. In eu odio at lectus pulvinar mollis. Vestibulum sem magna,
                    elementum ut, vestibulum eu, facilisis quis, arcu. Mauris a dolor. Nulla facilisi. Cum sociis
                    natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed blandit. Phasellus
                    pellentesque, ante nec iaculis dapibus, eros justo auctor lectus.</p>
            </div>
        </div>
        <div class="extra2 container">
            <div class="ebox1"><span class="fa fa-puzzle-piece"></span>
                <div class="title">
                    <h2>Etiam rhoncus volutpat</h2>
                    <span class="byline">Integer sit amet pede vel arcu aliquet pretium</span></div>
                <p>Integer nisl risus, sagittis convallis, rutrum id, elementum congue, nibh. Suspendisse dictum porta
                    lectus. Donec placerat odio vel elit. Nullam ante orci, pellentesque eget, tempus quis, ultrices in,
                    est. Sed vel tellus. Curabitur sem urna, consequat vel, suscipit in, mattis placerat, nulla. Sed ac
                    leo. Pellentesque imperdiet. Pellentesque viverra vulputate enim.</p>
            </div>
            <div class="ebox2"><span class="fa fa-comments-o"></span>
                <div class="title">
                    <h2>Mauris vulputate dolor</h2>
                    <span class="byline">Integer sit amet pede vel arcu aliquet pretium</span></div>
                <p>Proin gravida orci porttitor enim accumsan lacinia. Donec condimentum, urna non molestie semper,
                    ligula enim ornare nibh, quis laoreet eros quam eget ante. Aliquam libero. Vivamus nisl nibh,
                    iaculis vitae, viverra sit amet, ullamcorper vitae, turpis. Aliquam erat volutpat. Vestibulum dui
                    sem, pulvinar sed, imperdiet nec, iaculis nec, leo. </p>
            </div>
        </div>
    </div>
</div>

<%--<div id="footer-wrapper">--%>
    <%--<div id="footer" class="container">--%>

        <%--<p><a><fmt:message key="footer.msg"/></a>.</p>--%>
    <%--</div>--%>
<%--</div>--%>

<footer class="footer">
    <p class="text-muted">Footer</p>
</footer>

<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>

