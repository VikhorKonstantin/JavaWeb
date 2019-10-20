<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="error.title" var="title"/>
<utg:headTag titleAttr="${title}: ${pageContext.errorData.statusCode}"/>
<c:url value="/index.html" var="HomeRef"/>
<body>

<main class="main">
    <div class="row justify-content-center">
        <div class="card rounded-form" style="width:40rem">
            <div class="card-body">
                <h4 class="card-title">${title}: ${pageContext.errorData.statusCode}</h4>
                <h5 class="card-text">
                    <c:out value="${errorMessage}"/>
                </h5>
                <form action="${pageContext.request.contextPath}/">
                    <button class="btn btn-primary">
                        <fmt:message key="button.home"/></button>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
