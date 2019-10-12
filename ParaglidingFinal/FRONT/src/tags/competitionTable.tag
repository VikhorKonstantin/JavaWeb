<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="tableCompetitions" required="true" rtexprvalue="true"
              type="java.util.List<by.training.paragliding.bean.entity.Competition>" %>
<c:url value="/competition.html" var="competitionUrl"/>
<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">NAME</th>
        <th scope="col">DATE</th>
        <th scope="col">DISCIPLINE</th>
        <th scope="col">STATUS</th>
        <th scope="col">PARTICIPATION FEE</th>
        <th scope="col">LINK</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${tableCompetitions}" var="tableCompetition">
        <tr>
            <td>${tableCompetition.id}</td>
            <td>${tableCompetition.name}</td>
            <td>${tableCompetition.date}</td>
            <td>${tableCompetition.discipline}</td>
            <td>${tableCompetition.status}</td>
            <td>${tableCompetition.participationFee}</td>
            <td><a class="btn btn-primary"
                   href="${competitionUrl}?id=${tableCompetition.id}">More info</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>