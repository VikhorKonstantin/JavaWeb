<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/css/style.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <title>LibMain</title>
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
                <a class="nav-link" href="${sportsmenAllUrl}">UserList</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="userEdit.html">UserEdit</a>
            </li>
        </ul>
        <ul class="navbar-nav mr-l-4">
            <li class="nav-item"><a class="nav-link" href="logIn.html"> Login </a></li>
            <li class="nav-item"><a class="nav-link" href="singUp.html"> SingUp</a></li>
        </ul>
    </div>
</nav>

<header class="header d-flex flex-column justify-content-center">
    <div class="container-fluid">
        <h1 class="display-1">
            Paragliding
        </h1>
        <p class="lead">Paragliding rating...................</p>
    </div>
</header>

<main class="main">
    <div class="row justify-content-center">

        <div class="col-10 col-md-8 col-sm-8 col-lg-3 col-xl-3">
            <div class="container">
                <div class="card rounded-form">
                    <div class="card-body">
                        <div class="form-header blue accent-1">
                            <h3 class="mt-2"><em class="fas fa-envelope"></em> Write to us:</h3>
                        </div>
                        <form>
                            <div class="md-form">
                                <em class="fas fa-user prefix grey-text"></em>
                                <input type="text" id="form-name" class="form-control" pattern="^[a-zA-Z/s]+$"
                                       placeholder="Name" required>
                                <label class="required" for="form-name">Your name</label>
                            </div>
                            <div class="md-form">
                                <em class="fas fa-envelope prefix grey-text"></em>
                                <input type="text" id="form-email" class="form-control"
                                       pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                                       placeholder="some.email@smth.com" required>
                                <label class="required" for="form-email">Your e-mail</label>
                            </div>
                            <div class="md-form">
                                <em class="fas fa-tag prefix grey-text"></em>
                                <input type="text" id="form-Message" class="form-control" placeholder="Title"
                                       required>
                                <label class="required" for="form-Message">Title</label>
                            </div>
                            <div class="md-form">
                                <em class="fas fa-pencil-alt prefix grey-text"></em>
                                <textarea id="form-text" class="form-control md-textarea" rows="3"></textarea>
                                <label for="form-text">Message</label>
                            </div>
                            <div class="text-center">
                                <button class="btn btn-submit">Submit</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-10 col-md-6 col-sm-6 col-lg-6 col-xl-6">

            <div class=" d-flex justify-content-center">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <c:forEach items="${descriptions}" var = "description"/>
                        <div class="carousel-item">
                            <div class="card" style="width:400px">
                                <c:url var = "competeImg" value = "/img/competition.jpg"/>
                                <img class="card-img-top" src="${competeImg}" alt="Card image">
                                <div class="card-body">
                                    <h4 class="card-title">Competition</h4>
                                    <p class="card-text">${description}</p>
                                    <a href="#" class="btn btn-primary">Go somewhere</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                       data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                       data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

        </div>

        <div class="col-10 col-md-8 col-sm-8 col-lg-3 col-xl-3" style="overflow: hidden">
            <div class="container">
                <div class="d-flex flex-column justify-content-center">
                    <div class="row justify-content-center">
                        <iframe class="rounded-form"
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7925.670273270022!2d29.242583803635103!3d52.051644135655856!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46d6122d2b88b3cf%3A0x3bc639c8ce8cdba3!2z0KbQtdC90YLRgNCw0LvRjNC90LDRjyDQsdC40LHQu9C40L7RgtC10LrQsCDQuNC80LXQvdC4INCQLiDQoS4g0J_Rg9GI0LrQuNC90LA!5e0!3m2!1sru!2sby!4v1564253574917!5m2!1sru!2sby"
                                width="300" height="400" style="border:0" allowfullscreen></iframe>
                    </div>
                    <div class="row justify-content-center">
                        <div class="card" style="width: 18rem;">
                            <ul class="list-group list-group-flush">
                                <address>
                                    <li class="list-group-item"><em class="fas fa-phone-alt"></em>
                                        <a href="tel:375298359477">8 0236 37-70-49</a></li>
                                    <li class="list-group-item"><em class="fas fa-map-marked-alt"></em>
                                        улица Пролетарская 82, Мозырь
                                    </li>
                                    <li class="list-group-item"><em class="fas fa-clock"></em>
                                        Открыто с 08:00
                                    </li>
                                </address>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<footer>
    <div class="container">
        <div class="row">

            <div class="col-sm-3">
                <h3 class="footer_h3">Useful Links</h3>
                <ul class="footer_ul">
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">Home</a></li>
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="faq.php">FAQs</a></li>
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">About Us</a></li>
                </ul>
            </div>

            <div class="col-sm-3">
                <h3 class="footer_h3">Useful Links</h3>
                <ul class="footer_ul">
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">Space Adventure</a></li>
                </ul>
            </div>

            <div class="col-sm-3 col-sm-offset-3">
                <h3 class="footer_h3 fb">FAI</h3>
                <ul class="footer_ul">
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">FAI Rating</a></li>
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">FAI Main</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <div class="row">
                <ctg:info-time/>
                <ctg:hello role="${cookie}"/>
                <utg:hello/>
                <p>© Copyright 2019. All rights reserved</p>
            </div>
        </div>
    </div>
</footer>


<script src="https://kit.fontawesome.com/3ee7443726.js"></script>
<c:url var="jsUrl" value="/js/main.min.js"/>
<script src="${jsUrl}"></script>
</body>