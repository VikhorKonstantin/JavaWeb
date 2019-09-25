<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        table,
        th,
        td {
            border: 1px solid black;
        }
    </style>
    <title>
        Main
    </title>
</head>
<body>
<c:url value="sportsmen/id.html" var="sportsmenIdUrl"/>
<form action="${sportsmenIdUrl}" method="get">
    <input type="hidden" name="actionName" value="READ_BY_ID">
    <label>
        <input type="text" name="civlId">
    </label>
    <button type="submit">
        read
    </button>
    <p>
        <jsp:text>
            ${sportsman}
        </jsp:text>
    </p>
</form>
<c:url value="sportsmen/all.html" var="sportsmenAllUrl"/>
<form action="${sportsmenAllUrl}" method="get">
    <input type="hidden" name="actionName" value="sportsmen/all">
    <button type="submit">
        readAll
    </button>
    <table>
        <tr>
            <td>CIVL</td>
            <td>NAME</td>
            <td>SURNAME</td>
            <td>GENDER</td>
            <td>COUNTRY</td>
            <td>RATING</td>
            <td>IMAGE_PATH</td>
        </tr>

        <c:forEach items="${sportsmen}" var="sportsman">
            <tr>
                <td>${sportsman.civlId}</td>
                <td>${sportsman.name}</td>
                <td>${sportsman.surname}</td>
                <td>${sportsman.gender}</td>
                <td>${sportsman.countryCode.name}</td>
                <td>${sportsman.rating}</td>
                <td>${sportsman.imagePath}</td>
            </tr>
        </c:forEach>
    </table>
</form>
$END$
</body>
</html>
