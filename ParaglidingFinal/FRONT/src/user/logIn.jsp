<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="login.title" var="title"/>
<utg:headTag titleAttr="${title}"/>
<c:url value="/index.html" var="HomeRef" scope="page"/>
<c:url var="singUp" value="/singUp.html"/>
<body>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-light">

    <a class="navbar-brand" href="${HomeRef}">
        <c:url value="/img/logo.png" var="logoImg"/>
        <img src="${logoImg}" width="75" height="75" alt="logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="${HomeRef}">
                    <fmt:message key="home.title"/></a>
            </li>
            <li class="nav-item">
                <c:url value="/sportsmen/all.html" var="sportsmenAllUrl"/>
                <a class="nav-link" href="${sportsmenAllUrl}">
                    <fmt:message key="sportsmen.title"/></a>
            </li>
            <li class="nav-item">
                <c:url value="/competition/all.html" var="competitionsAllUrl"/>
                <a class="nav-link" href="${competitionsAllUrl}">
                    <fmt:message key="competitions.title"/></a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <c:choose>
                <c:when test="${User != null}">
                    <c:url var="userPage" value="/user/account.html"/>
                    <li class="nav-item"><a class="nav-link" href="${userPage}"> <fmt:message key="account.title"/> </a>
                    </li>
                    <c:url var="logIn" value="/user/logOut.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="logout.title"/> </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:url var="logIn" value="/logIn.html"/>
                    <li class="nav-item"><a class="nav-link active" href="${logIn}"> <fmt:message
                            key="login.title"/> </a></li>
                    <c:url var="singUp" value="/singUp.html"/>
                    <li class="nav-item"><a class="nav-link" href="${singUp}"> <fmt:message key="singup.title"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:url var="localeChangeUrl" value="/localeChange.html"/>
            <form action="${localeChangeUrl}" method="POST">
                <button class="btn btn-primary" value="en_US" name="locale">EN</button>
                <button class="btn btn-primary" value="ru_RU" name="locale">RU</button>
            </form>
        </ul>
    </div>

</nav>
<main class="main">
    <div class="container d-flex justify-content-center">
        <div class="card rounded-form">
            <div class="card-body col-12">
                <div class="login-form">

                    <utg:messageAlertt message="${message}"/>
                    <c:url var="logInAction" value="/user/logIn.html"/>
                    <form action="${logInAction}" method="post">
                        <div class="form-group">
                            <label for="emailInput">E-mail</label>
                            <input id="emailInput" type="email" aria-describedby="emailHelp"
                                   name="email" class="form-control"
                                   placeholder="some.email@smth.com"
                                   title="some.email@smth.com"
                                   required>

                        </div>
                        <div class="form-group">
                            <label for="passwordInput"><fmt:message key="password"/></label>
                            <input type="password" name="password" id="passwordInput"
                                   class="form-control"
                                   placeholder="******"
                                   pattern="[0-9a-zA-z]{8,32}"
                                   title="Latin letters, numbers. Min=8, Max=32" required>
                        </div>
                        <button type="submit" class="btn btn-submit glo"><fmt:message key="login.title"/></button>
                        <a class="btn btn-secondary glo"
                           href="${singUp}"><fmt:message key="register"/></a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="../footer.jsp"/>
</body>