<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ attribute name="tableCompetitions" required="true" rtexprvalue="true"
              type="java.util.List<by.training.paragliding.bean.entity.Competition>" %>
<c:url value="/competition.html" var="competitionUrl"/>
<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col"><fmt:message key="competition.name"/></th>
        <th scope="col"><fmt:message key="competition.date"/></th>
        <th scope="col"><fmt:message key="competition.discipline"/></th>
        <th scope="col"><fmt:message key="competition.status"/></th>
        <th scope="col"><fmt:message key="competition.fee"/></th>
        <th scope="col"><fmt:message key="competition.link"/></th>
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
                   href="${competitionUrl}?id=${tableCompetition.id}">
                <fmt:message key="competition.info"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>