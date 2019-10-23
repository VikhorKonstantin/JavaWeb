<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="pageIndex" rtexprvalue="true" type="java.lang.Integer" required="true" %>
<%@ attribute name="lastPageIndex" rtexprvalue="true" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageURL" rtexprvalue="true" type="java.lang.String" required="true" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="row">
    <div class="col-sm-12 mx-auto">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <c:url var="First" value="${pageURL}.html">
                    <c:param name="pageIndex" value="1"/>
                </c:url>
                <a class="page-link" href="${First}"><fmt:message key="page.first"/> </a>
            </li>

            <c:choose>
                <c:when test="${pageIndex == 1}">
            <li class="page-item disabled">
                </c:when>
                <c:otherwise>
            <li class="page-item">
                </c:otherwise>
            </c:choose>
                <c:url value="${pageURL}.html" var="Previous">
                    <c:param name="pageIndex" value="${pageIndex - 1}"/>
                </c:url>
                <a class="page-link" href="${Previous}"><fmt:message key="page.prev"/> </a>
            </li>
            <c:choose>
                <c:when test="${pageIndex >= lastPageIndex}">
            <li class="page-item disabled">
                </c:when>
                <c:otherwise>
            <li class="page-item">
                </c:otherwise>
            </c:choose>
                <c:url var="Next" value="${pageURL}.html">
                    <c:param name="pageIndex" value="${pageIndex + 1}"/>
                </c:url>
                <a class="page-link" href="${Next}"><fmt:message key="page.next"/> </a>
            </li>
            <li class="page-item">
                <c:url var="Last" value="${pageURL}.html">
                    <c:param name="pageIndex" value="${lastPageIndex}"/>
                </c:url>
                <a class="page-link" href="${Last}"><fmt:message key="page.last"/> </a>
            </li>
        </ul>
    </div>
</div>