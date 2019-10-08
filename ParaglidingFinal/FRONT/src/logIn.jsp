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
                    <a class="nav-link" href="main.jsp">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="userList.jsp">UserList</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="userEdit.html">UserEdit</a>
                </li>
            </ul>
            <ul class="navbar-nav mr-l-4">
                <li class="nav-item  active"><a class="nav-link" href="logIn.jsp"> Login <span
                            class="sr-only">(current)</span></a></li>
                <li class="nav-item"><a class="nav-link" href="singUp.jsp"> SingUp</a></li>
            </ul>
        </div>

    </nav>
    <main class="main">
        <div class="container d-flex justify-content-center">
            <div class="card rounded-form">
                <div class="card-body col-12">
                    <div class="login-form">
                        <c:url var = "logInAction" value="/user/logIn.html"/>
                        <form action="${logInAction}" method="post">
                            <div class="form-group">
                                <label>User Name</label>
                                <input type="text" class="form-control" placeholder="User Name" required>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" placeholder="Password" required>
                            </div>
                            <button type="submit" class="btn btn-submit glo">Login</button>
                            <a class="btn btn-secondary glo"
                                    href="singUp.jsp">Register</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>



    <script src="https://kit.fontawesome.com/3ee7443726.js"></script>
    <script src="assets/js/main.min.js"></script>
</body>