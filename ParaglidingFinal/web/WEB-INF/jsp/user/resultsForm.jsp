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
                    <c:url var="userPage" value="/user/account.html"/>
                    <li class="nav-item"><a class="nav-link" href="${userPage}"> <fmt:message key="account.title"/> </a>
                    </li>
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

            <div class="card-body">
                <h4 class="card-title">${competition.name} <fmt:message key="competition.results"/></h4>
                <c:url var="resultAction" value="/results.html"/>
                <form action="${resultAction}" method="post">
                    <input type="hidden" name="competitionId" value="${competition.id}"/>
                    <table class="table table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">INDEX</th>
                            <th scope="col">CIVL_ID</th>
                            <th scope="col">NAME</th>
                            <th scope="col">SCORE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${participants}" var="sportsman" varStatus="loop">
                            <tr>
                                <td>${loop.index}</td>
                                <td>${sportsman.civlId}</td>
                                <td>${sportsman.name}</td>
                                <td>
                                    <div class="form-group">
                                        <label for="score">Score</label>
                                        <input type="number" name="score${loop.index}" id="score"
                                               class="form-control" placeholder="Score"
                                               required>
                                        <input type="hidden" name="sportsmanId${loop.index}"
                                               value="${sportsman.civlId}"/>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-row">
                        <button class="btn btn-submit" type="submit">Set results</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
<c:import url="../footer.jsp"/>
</body>