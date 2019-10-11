<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/css/style.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <script src="https://kit.fontawesome.com/3ee7443726.js"></script>
    <c:url var="jsUrl" value="/js/main.js"/>
    <script src="${jsUrl}"></script>
    <title>Users</title>
</head>

<body>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-light">
    <a class="navbar-brand  active" href="/index.html">
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
                <c:url value="/index.html" var="HomeRef"/>
                <a class="nav-link" href="${HomeRef}">Home </a>
            </li>
            <li class="nav-item">
                <c:url value="/sportsmen/all.html" var="sportsmenAllUrl"/>
                <a class="nav-link  active" href="${sportsmenAllUrl}">Sportsmen<span
                        class="sr-only">(current)</span></a>
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
    <div class="container d-flex justify-content-center">
        <div class="card rounded-form">
            <div class="card-body col-12">
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">CIVL</th>
                        <th scope="col">NAME</th>
                        <th scope="col">SURNAME</th>
                        <th scope="col">GENDER</th>
                        <th scope="col">COUNTRY</th>
                        <th scope="col">RATING</th>
                        <th scope="col">IMAGE_PATH</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sportsmen}" var="sportsman">
                        <tr>
                            <td>${sportsman.civlId}</td>
                            <td>${sportsman.name}</td>
                            <td>${sportsman.surname}</td>
                            <td>${sportsman.gender}</td>
                            <td><em class="fas fa-map-marked-alt"></em>
                                    ${sportsman.countryCode.name}</td>
                            <td>${sportsman.rating}</td>
                            <td>${sportsman.imagePath}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</main>

</body>