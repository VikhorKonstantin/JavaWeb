<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ attribute name="sportsmen" required="true" rtexprvalue="true"
              type="java.util.List<by.training.paragliding.bean.entity.Sportsman>" %>
<%@ attribute name="currentId" required="false" rtexprvalue="true"
              type="java.lang.Integer" %>
<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">CIVL</th>
        <th scope="col"><fmt:message key="sportsman.name"/></th>
        <th scope="col"><fmt:message key="sportsman.surname"/></th>
        <th scope="col"><fmt:message key="sportsman.gender"/></th>
        <th scope="col"><fmt:message key="sportsman.country"/></th>
        <th scope="col"><fmt:message key="sportsman.rating"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sportsmen}" var="sportsman">
        <c:choose>
            <c:when test="${sportsman.civlId == currentId}">
                <tr class="table-danger">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>

            <td>${sportsman.civlId}</td>
            <td>${sportsman.name}</td>
            <td>${sportsman.surname}</td>
            <td>${sportsman.gender}</td>
            <td><em class="fas fa-map-marked-alt"></em>
                    ${sportsman.countryCode.name}</td>
            <td>${sportsman.rating}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
