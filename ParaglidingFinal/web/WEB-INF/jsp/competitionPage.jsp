<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="competitions.title" var="title"/>
<utg:headTag titleAttr="${title}"/>
<c:url value="/index.html" var="HomeRef"/>
<body>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-light">
    <a class="navbar-brand  active" href="${HomeRef}">
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
                    <c:url var="logIn" value="/user/logOut.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="logout.title"/> </a></li>
                </c:when>
                <c:otherwise>
                    <c:url var="logIn" value="/logIn.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="login.title"/> </a></li>
                    <c:url var="singUp" value="/singUp.html"/>
                    <li class="nav-item"><a class="nav-link" href="${singUp}"> <fmt:message key="singup.title"/></a></li>
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
    <div class="row justify-content-center">
        <div class="card rounded-form" style="width:400px">
            <c:url var="competeImg" value="/img/competition.jpg"/>
            <img class="card-img-top" src="${competeImg}" alt="Card image">
            <c:if test="${param.message != null}">
                <utg:messageAlertt message="${param.message}"/>
            </c:if>
            <div class="card-body">
                <h4 class="card-title">${competition.name}</h4>
                <h5 class="card-text">${competition.description}</h5>
                <p class="card-text"><fmt:message key="competition.date"/>: ${competition.date}</p>
                <p class="card-text"><fmt:message key="competition.status"/>: ${competition.status}</p>
                <p class="card-text"><fmt:message key="competition.discipline"/>: ${competition.discipline}</p>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-4">
                            <c:if test="${User != null}">
                                <c:url var="editUrl" value="/competition/edit.html">
                                    <c:param name="competitionId" value="${competition.id}"/>
                                </c:url>
                                <c:choose>
                                    <c:when test="${User.role == 'REGISTERED_USER'}">
                                        <a href="${editUrl}" class="btn btn-primary"><fmt:message key="competition.edit"/></a>
                                    </c:when>
                                    <c:when test="${User.role == 'REGISTERED_SPORTSMAN'}">
                                        <c:url value="/application.html" var="applyUrl"/>
                                        <form action="${applyUrl}" method="post">
                                            <input type="hidden"
                                                   name="competitionId" value="${competition.id}"/>
                                            <input type="hidden"
                                                   name="sportsmanId"
                                                   value="${User.sportsman.civlId}"/>
                                            <button id="applyButton" type="submit" class="btn btn-primary">
                                                <fmt:message key="competition.apply"/>
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${editUrl}" class="btn btn-primary">
                                            <fmt:message key="competition.edit"/></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                        <div class="col-4">
                            <c:url var="participants" value="/sportsmen/participants.html">
                                <c:param name="competitionId" value="${competition.id}"/>
                            </c:url>
                            <a class="btn btn-primary" href="${participants}">
                                <fmt:message key="competition.participants"/></a>
                        </div>
                        <c:if test="${competition.status == 'FINISHED'}">
                            <div class="col-4">
                                <c:url var="results" value="/results.html">
                                    <c:param name="competitionId" value="${competition.id}"/>
                                </c:url>
                                <a class="btn btn-primary" href="${results}">
                                    <fmt:message key="competition.results"/>
                                </a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>