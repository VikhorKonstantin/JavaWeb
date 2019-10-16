<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="titleAttr" required="true" rtexprvalue="true"
              type="java.lang.String" %>
<c:url value="/index.html" var="HomeRef"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/css/style.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <script src="https://kit.fontawesome.com/3ee7443726.js"></script>
    <c:url var="jsUrl" value="/js/main.js"/>
    <script src="${jsUrl}"></script>
    <title>${titleAttr}</title>
</head>