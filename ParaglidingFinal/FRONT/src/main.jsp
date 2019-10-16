<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="main.title" var="title"/>
<utg:headTag titleAttr="${title}"/>
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
                    <fmt:message key="home.title"/> <span class="sr-only">(current)</span></a>
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



        <div class="col-10 col-md-6 col-sm-6 col-lg-6 col-xl-6">

            <div class=" d-flex justify-content-center">
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach items="${competitions}" var="competition" varStatus="counter">
                            <c:choose>
                                <c:when test="${counter.first}">
                                    <div class="carousel-item active">
                                        <div class="card rounded-form" style="width:400px">
                                            <c:url var="competeImg" value="/img/competition.jpg"/>
                                            <img class="card-img-top" src="${competeImg}" alt="Card image">
                                            <div class="card-body">
                                                <h4 class="card-title">${competition.name}</h4>
                                                <p class="card-text"><c:out value="${competition.description}"/></p>
                                                <c:url var="moreInfoUrl" value="/competition.html">
                                                    <c:param name="id" value="${competition.id}"/>
                                                </c:url>
                                                <a href="${moreInfoUrl}" class="btn btn-primary"><fmt:message key="competitions.more"/></a>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="carousel-item">
                                        <div class="card rounded-form" style="width:400px">
                                            <c:url var="competeImg" value="/img/competition.jpg"/>
                                            <img class="card-img-top" src="${competeImg}" alt="Card image">
                                            <div class="card-body">
                                                <h4 class="card-title">${competition.name}</h4>
                                                <p class="card-text"><c:out value="${competition.description}"/></p>
                                                <c:url var="moreInfoUrl" value="/competition.html">
                                                    <c:param name="id" value="${competition.id}"/>
                                                </c:url>
                                                <a href="${moreInfoUrl}" class="btn btn-primary"><fmt:message key="competitions.more"/></a>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

        </div>

    </div>
</main>
<jsp:include page="footer.jsp"/>

</body>