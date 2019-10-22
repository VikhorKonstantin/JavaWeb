<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="account.title" var="title"/>
<utg:headTag titleAttr="${title}"/>
<c:url value="/index.html" var="HomeRef"/>
<body>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-light">

    <a class="navbar-brand  active" href="${HomeRef}">
        <c:url value="/img/logo.png" var="logoImg"/>
        <img src="${logoImg}" width="75" height="75" alt="logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link " href="${HomeRef}">
                    <fmt:message key="home.title"/></a>
            </li>
            <li class="nav-item">
                <c:url value="/sportsmen/all.html" var="sportsmenAllUrl"/>
                <a class="nav-link" href="${sportsmenAllUrl}"><fmt:message key="sportsmen.title"/></a>
            </li>
            <li class="nav-item">
                <c:url value="/competition/all.html" var="competitionsAllUrl"/>
                <a class="nav-link" href="${competitionsAllUrl}"><fmt:message key="competitions.title"/></a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <c:choose>
                <c:when test="${User != null}">
                    <c:url var="userPage" value="/user/page.html"/>
                    <li class="nav-item"><a class="nav-link active" href="${userPage}"> <fmt:message
                            key="account.title"/> </a>
                    </li>
                    <c:url var="logIn" value="/user/logOut.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="logout.title"/> </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:url var="logIn" value="/logIn.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="login.title"/> </a></li>
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
    <c:choose>
        <c:when test="${User.role == 'REGISTERED_SPORTSMAN'}">
            <div class="container">
                <div class="card rounded-form">
                    <div class="card-body col-12">
                        <div class="card-title">
                            <h2 class="text-center">
                                <fmt:message key="account.competitors"/>:
                            </h2>
                        </div>
                        <div class="card-text">
                            <utg:sportsmanTable sportsmen="${competitors}"
                                                currentId ="${User.sportsman.civlId}"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="card rounded-form">
                    <div class="card-body col-12">
                        <div class="card-text">
                            <h2 class="text-center">
                                <fmt:message key="account.competitions"/>:
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
            <utg:compList editable="true" finishedCompetitions="${finishedComps}"
                          futureCompetitions="${futureComps}"/>
        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="card rounded-form">
                    <div class="card-body col-12">
                        <div class="card-title">
                            <h2 class="text-center">
                                <fmt:message key="account.competitions"/>:
                                <c:url var="addUrl" value="/competition/edit.html"/>
                                <a class="btn btn-primary"
                                   href="${addUrl}">
                                    <fmt:message key="account.addComp"/></a>
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
            <utg:compList finishedCompetitions="${finishedComps}"
                          futureCompetitions="${futureComps}"/>
        </c:otherwise>
    </c:choose>

</main>