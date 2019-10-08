<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/css/style.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <title>SingUp</title>
    <c:url var="jsUrl" value="/js/main.js"/>
    <script src="${jsUrl}"></script>
</head>
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
                <a class="nav-link" href="${HomeRef}">Home </a>
            </li>
            <li class="nav-item">
                <c:url value="/sportsmen/all.html" var="sportsmenAllUrl"/>
                <a class="nav-link" href="${sportsmenAllUrl}">UserList</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="userEdit.html">UserEdit</a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <li class="nav-item"><a class="nav-link" href="logIn.jsp"> Login</a></li>
            <li class="nav-item  active"><a class="nav-link" href="#"> SingUp <span
                    class="sr-only">(current)</span></a></li>
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
                        <div class="col-md-4 mb-3">
                            <label class="required" for="validationServerE-mail">E-mail</label>
                            <input name="email" type="text" class="form-control" id="validationServerE-mail"
                                   placeholder="some.email@smth.com"
                                   pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                                   title="some.email@smth.com" required>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="required" for="validationServerE-mail">Password</label>
                            <input name="password" type="text" class="form-control" id="validationServerPassword"
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
                            <label class="form-check-label" for="sportsmenCheck">Sportsman</label>
                        </div>
                    </div>
                    <div id="sportsmenForm" class="form-row">
                        <div class="col-md-4 mb-3">
                            <label class="required" for="validationServer01">First name</label>
                            <input type="text" class="form-control" id="validationServer01" placeholder="First name"
                                   pattern="^[a-zA-Z\s]+$" title="Should only contain letters" required>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="required" for="validationServer02">Last name</label>
                            <input type="text" class="form-control" id="validationServer02" placeholder="Last name"
                                   pattern="^[a-zA-Z\s]+$" title="Should only contain letters" required>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="validationServer03">Country</label>
                            <input type="text" class="form-control" id="validationServer03" placeholder="Country"
                                   pattern="^[a-zA-Z\s]+$" title="Should only contain letters" required>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="validationServer04">FAI Rating</label>
                            <input type="text" class="form-control" id="validationServer04" placeholder="Rating"
                                   pattern="^.{2,6}[0-9.]+$" title="Should only contain letters" required>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                            <label class="form-check-label" for="inlineRadio1">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
                            <label class="form-check-label" for="inlineRadio2">Female</label>
                        </div>
                    </div>
                    <button class="btn btn-submit" type="submit">Submit form</button>
                </form>
            </div>
        </div>
    </div>
</main>

<script src="https://kit.fontawesome.com/3ee7443726.js"></script>
</body>