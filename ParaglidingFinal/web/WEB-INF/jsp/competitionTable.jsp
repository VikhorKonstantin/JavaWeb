<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">NAME</th>
        <th scope="col">DATE</th>
        <th scope="col">DISCIPLINE</th>
        <th scope="col">STATUS</th>
        <th scope="col">PARTICIPATION FEE</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${competitions}" var="competition">
        <tr>
            <td>${competition.id}</td>
            <td>${competition.name}</td>
            <td>${competition.date}</td>
            <td>${competition.discipline}</td>
            <td>${competition.status}</td>
            <td>${competition.participationFee}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>