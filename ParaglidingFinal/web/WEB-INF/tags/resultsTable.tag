<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ attribute name="participants" required="true" rtexprvalue="true"
              type="java.util.List<by.training.paragliding.bean.entity.Sportsman>" %>
<%@ attribute name="isSave" required="true" rtexprvalue="true" type="java.lang.Boolean" %>

<table class="table table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">INDEX</th>
        <th scope="col">CIVL_ID</th>
        <th scope="col"><fmt:message key="competition.name"/></th>
        <th scope="col"><fmt:message key="sportsman.score"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${participants}" var="sportsman" varStatus="loop">
        <tr>
            <td>${loop.index}</td>
            <td>${sportsman.civlId}</td>
            <td>${sportsman.name}</td>
            <td>
                <div class="form-group">
                    <label for="score"><fmt:message key="sportsman.score"/></label>
                    <input type="number" name="score${loop.index}" id="score"
                           class="form-control" placeholder="Score"
                           required>
                    <input type="hidden" name="sportsmanId${loop.index}"
                           value="${sportsman.civlId}"/>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>