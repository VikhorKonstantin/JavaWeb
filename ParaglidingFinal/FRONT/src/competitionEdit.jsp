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
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="logout.title"/> </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <c:url var="logIn" value="/logIn.html"/>
                    <li class="nav-item"><a class="nav-link" href="${logIn}"> <fmt:message key="login.title"/> </a></li>
                    <c:url var="singUp" value="/singUp.html"/>
                    <li class="nav-item"><a class="nav-link" href="${singUp}"> <fmt:message key="singup.title"/></a>
                    </li>
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
                <c:choose>
                    <c:when test="${competition == null}">
                        <c:url var="competitionAdd"
                               value="/competition/add.html"/>
                    <form action="${competitionAdd}" id="editForm" method="post">
                    </c:when>
                    <c:otherwise>
                        <c:url var="competitionEdit"
                               value="/competition/edit.html"/>
                        <form action="${competitionAdd}" id="editForm" method="post">
                    </c:otherwise>
                </c:choose>

                    <input type="hidden" name="id" value="${competition.id}"/>
                    <div class="form-row">
                        <div class="col-md-4 mb-4">
                            <label class="required" for="nameId"><fmt:message key="competition.name"/> </label>
                            <input id="nameId" type="text" name="name" class="form-control"
                                   placeholder="Name"
                                   title="Latin letters(250 max)"
                                   pattern="^[0-9.?!a-zA-Z\s]{0,250}$" value="${competition.name}" required>

                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="dateId"><fmt:message key="competition.date"/></label>
                            <input id="dateId" type="text" name="date" class="form-control"
                                   placeholder="Last name"
                                   title="year-mm-dd"
                                   pattern="^\d{4}-\d{1,2}-\d{1,2}$" value="${competition.date}" required>

                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="required" for="feeId"><fmt:message key="competition.fee"/></label>
                            <input id="feeId" type="text" class="form-control"
                                   placeholder="Fee" name="fee"
                                   title="52.212"
                                   pattern="^[0-9]{0,4}.?[0-9]*$"
                                   value="${competition.participationFee}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-4 mb-4">
                            <label class="required" for="inputStatus"><fmt:message key="competition.status"/></label>
                            <select name="status" id="inputStatus" class="form-control">
                                <option selected>${competition.status}</option>
                                <option>ANNOUNCED</option>
                                <option>REGISTRATION_OPENED</option>
                                <option>REGISTRATION_CLOSED</option>
                                <option>UNDERWAY</option>
                                <option>FINISHED</option>
                            </select>
                        </div>
                        <div class="col-md-4 mb-4">
                            <label class="required" for="inputDiscipline"><fmt:message
                                    key="competition.discipline"/></label>
                            <select name="discipline" id="inputDiscipline" class="form-control">
                                <option selected>${competition.discipline}</option>
                                <option>PG_AC</option>
                                <option>PG</option>
                                <option>PG_AER_SOLO</option>
                                <option>PG_AER_SYNCRO</option>
                                <option>HG_C1</option>
                                <option>HG_C2</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="Description"><fmt:message key="competition.description"/></label>
                        <textarea name="description"
                                  class="form-control bordering" id="Description"
                                  rows="3"><c:out value="${competition.description}"/></textarea>
                    </div>
                    <button class="btn btn-submit" type="submit">
                        <fmt:message key="submit"/></button>
                    <c:if test="${competition.status != 'FINISHED'}">
                        <c:url var="viewResultFormPage" value="/resultsPage.html">
                            <c:param name="competitionId" value="${competition.id}"/>
                        </c:url>
                        <a href="${viewResultFormPage}" class="btn btn-submit">
                            <fmt:message key="competition.setResults"/></a>
                    </c:if>
                </form>

            </div>
        </div>
    </div>

</main>
<c:import url="footer.jsp"/>
</body>