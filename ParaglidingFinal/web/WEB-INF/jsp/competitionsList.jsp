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
                <a class="nav-link" href="${sportsmenAllUrl}">Sportsmen</a>
            </li>
            <li class="nav-item active">
                <c:url value="competition/all.html" var="competitionsAllUrl"/>
                <a class="nav-link" href="${competitionsAllUrl}">Competitions<span
                        class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <c:choose>
                <c:when test="${User != null}">
                    <c:url var="logIn" value="/user/logOut.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> LogOut </a></li>
                </c:when>
                <c:otherwise>
                    <c:url var="logIn" value="/logIn.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> Login </a></li>
                    <c:url var="singUp" value="/singUp.html"/>
                    <li class="nav-item"><a class="nav-link" href="${singUp}"> SingUp</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>

</nav>
<main class="main">
    <div class="container d-flex justify-content-center">
        <div class="card rounded-form">
            <div class="card-body col-12">
                <div class="list-group" id="myList" role="tablist">
                    <a class="list-group-item list-group-item-action list-inline-item active" data-toggle="list"
                       href="#finished"
                       role="tab">Finished</a>
                    <a class="list-group-item list-group-item-action list-inline-item" data-toggle="list" href="#future"
                       role="tab">Future</a>
                </div>
                <c:url value="/competition.html" var="competitionUrl"/>
                <div class="tab-content">
                    <div class="tab-pane active" id="finished" role="tabpanel">
                        <table class="table table-hover">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">NAME</th>
                                <th scope="col">DATE</th>
                                <th scope="col">DISCIPLINE</th>
                                <th scope="col">STATUS</th>
                                <th scope="col">PARTICIPATION FEE</th>
                                <th scope="col">LINK</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${finishedComps}" var="competition">
                                <tr>
                                    <td>${competition.id}</td>
                                    <td>${competition.name}</td>
                                    <td>${competition.date}</td>
                                    <td>${competition.discipline}</td>
                                    <td>${competition.status}</td>
                                    <td>${competition.participationFee}</td>
                                    <td><a class="btn btn-primary"
                                           href="${competitionUrl}?id=${competition.id}">More info</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="future" role="tabpanel">
                        <table class="table table-hover">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">NAME</th>
                                <th scope="col">DATE</th>
                                <th scope="col">DISCIPLINE</th>
                                <th scope="col">STATUS</th>
                                <th scope="col">PARTICIPATION FEE</th>
                                <th scope="col">LINK</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${futureComps}" var="competition">
                                <tr>
                                    <td>${competition.id}</td>
                                    <td>${competition.name}</td>
                                    <td>${competition.date}</td>
                                    <td>${competition.discipline}</td>
                                    <td>${competition.status}</td>
                                    <td>${competition.participationFee}</td>
                                    <td><a class="btn btn-primary"
                                           href="${competitionUrl}?id=${competition.id}">More info</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

</body>