<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/css/style.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <title>LogIn</title>
    <c:url var="jsUrl" value="/js/main.js"/>
    <script src="${jsUrl}"></script>
</head>
<c:url value="/index.html" var="HomeRef" scope="page"/>
<c:url var="singUp" value="/singUp.html"/>
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
                <a class="nav-link" href="${HomeRef}">Home </a>
            </li>
            <li class="nav-item">
                <c:url value="/sportsmen/all.html" var="sportsmenAllUrl"/>
                <a class="nav-link" href="${sportsmenAllUrl}">Sportsmen</a>
            </li>
            <li class="nav-item">
                <c:url value="/competition/all.html" var="competitionsAllUrl"/>
                <a class="nav-link" href="${competitionsAllUrl}">Competitions</a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <c:url var="logIn" value="/logIn.html"/>
            <li class="nav-item"><a class="nav-link" href="${logIn}"> Login </a></li>
            <li class="nav-item"><a class="nav-link" href="${singUp}"> SingUp</a></li>
        </ul>
    </div>

</nav>
<main class="main">
    <div class="container d-flex justify-content-center">
        <div class="card rounded-form">
            <div class="card-body col-12">
                <div class="login-form">

                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>${message}</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:url var="logInAction" value="/user/logIn.html"/>
                    <form action="${logInAction}" method="post">
                        <div class="form-group">
                            <label for="emailInput">E-mail</label>
                                <input id="emailInput" type="email" aria-describedby="emailHelp"
                                       name="email" class="form-control"
                                       placeholder="E-mail" required>

                        </div>
                        <div class="form-group">
                            <label for="passwordInput">Password</label>
                                <input type="password" name="password" id="passwordInput"
                                       class="form-control" placeholder="Password"
                                       required>

                        </div>
                        <button type="submit" class="btn btn-submit glo">Login</button>
                        <a class="btn btn-secondary glo"
                           href="${singUp}">Register</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

</body>