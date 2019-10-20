<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="singup.title" var="title"/>
<utg:headTag titleAttr="${title}"/>
<c:url value="/index.html" var="HomeRef"/>
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
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="logout.title"/> </a></li>
                </c:when>
                <c:otherwise>
                    <c:url var="logIn" value="/logIn.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="login.title"/> </a></li>
                    <c:url var="singUp" value="/singUp.html"/>
                    <li class="nav-item"><a class="nav-link active" href="${singUp}"> <fmt:message key="singup.title"/></a></li>
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
                <c:url var="singUpAction" value="/user/singUp.html"/>
                <form action="${singUpAction}" method="post">
                    <div class="form-row">
                        <div class="col-md-5 mb-4">
                            <label class="required" for="validationServerE-mail">E-mail</label>
                            <input name="email" type="email" class="form-control" id="validationServerE-mail"
                                   placeholder="some.email@smth.com"
                                   title="some.email@smth.com" required>
                        </div>
                        <div class="col-md-5 mb-4">
                            <label class="required" for="validationServerE-mail">
                                <fmt:message key="password"/></label>
                            <input name="password" type="password" class="form-control"
                                   id="validationServerPassword"
                                   placeholder="Latin letters, numbers. Min=8, Max=32"
                                   pattern="[0-9a-zA-z]{8,32}"
                                   title="Latin letters, numbers. Min=8, Max=32" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-check">
                            <input name="isSportsman" type="checkbox"
                                   class="form-check-input"
                                   id="sportsmenCheck" value="true" checked>
                            <label class="form-check-label" for="sportsmenCheck">
                                <fmt:message key="sportsman"/> </label>
                        </div>
                    </div>
                    <div id="sportsmenForm" class="form-row">
                        <div class="col-md-4 mb-3">
                            <label class="required" for="validationServer01">
                                <fmt:message key="sportsman.name"/> </label>
                            <input type="text"
                                   name="name"
                                   class="form-control" id="validationServer01"
                                   placeholder="First name"
                                   pattern="^[a-zA-Z\s]+$" title="Should only contain letters" required>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="required" for="validationServer02">
                                <fmt:message key="sportsman.surname"/>
                            </label>
                            <input type="text"
                                   name="surname"
                                   class="form-control" id="validationServer02" placeholder="Last name"
                                   pattern="^[a-zA-Z\s]+$" title="Should only contain letters" required>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="validationServer03"><fmt:message key="sportsman.country"/></label>
                            <input type="text"
                                   name="country"
                                   class="form-control" id="validationServer03" placeholder="Country"
                                   pattern="^[a-zA-Z\s]+$" title="Should only contain letters" required>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="validationServer04">
                                <fmt:message key="sportsman.rating"/>
                            </label>
                            <input type="text"
                                   name="rating"
                                   class="form-control" id="validationServer04" placeholder="Rating"
                                   pattern="^[0-9]{0,4}\.[0-9]$"
                                   title="Should have from 0 to 4 numbers before . and 1 after" required>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="validationServer04">CIVL_ID</label>
                            <input type="text"
                                   name="id"
                                   class="form-control" id="validationServer05" placeholder="CIVL_ID"
                                   pattern="^[0-9]{2,6}$" title="Should only contain letters" required>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio" name="gender" id="inlineRadio1"
                                   value="M">
                            <label class="form-check-label" for="inlineRadio1">
                                <fmt:message key="sportsman.male"/>
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="inlineRadio2"
                                   value="F">
                            <label class="form-check-label" for="inlineRadio2">
                                <fmt:message key="sportsman.female"/>
                            </label>
                        </div>
                    </div>
                    <button class="btn btn-submit" type="submit"><fmt:message key="submit"/></button>
                </form>
            </div>
        </div>
    </div>
</main>
<c:import url="footer.jsp"/>
</body>