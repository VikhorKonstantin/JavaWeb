<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/css/style.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <script src="https://kit.fontawesome.com/3ee7443726.js"></script>
    <c:url var="jsUrl" value="/js/main.js"/>
    <script src="${jsUrl}"></script>
    <title>Paragliding</title>
</head>
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
                <a class="nav-link  active" href="${HomeRef}">
                    Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <c:url value="/sportsmen/all.html" var="sportsmenAllUrl"/>
                <a class="nav-link" href="${sportsmenAllUrl}">Sportsmen</a>
            </li>
            <li class="nav-item">
                <c:url value="/competitions/all.html" var="competitionsAllUrl"/>
                <a class="nav-link" href="${competitionsAllUrl}">Competitions</a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <c:url var="logIn" value="/logIn.html"/>
            <li class="nav-item"><a class="nav-link" href="${logIn}"> Login </a></li>
            <c:url var="singUp" value="/singUp.html"/>
            <li class="nav-item"><a class="nav-link" href="${singUp}"> SingUp</a></li>
        </ul>
    </div>
</nav>

<main class="main">
    <div class="container">
        <div class="card rounded-form" style="width:400px">
            <c:url var="competeImg" value="/img/competition.jpg"/>
            <img class="card-img-top" src="${competeImg}" alt="Card image">
            <div class="card-body">
                <h4 class="card-title">${competition.name}</h4>
                <p class="card-text">${competition.description}</p>
                <p class="card-text">Date: ${competition.date}</p>
                <p class="card-text">Status: ${competition.status}</p>
                <p class="card-text">Discipline: ${competition.discipline}</p>
                <c:if test="${User != null}">
                    <c:choose>
                        <c:when test="${User.role == REGISTERED_USER}">
                            <a class="btn-submit" href="">Edit</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn-submit" href="">Join</a>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </div>
</main>