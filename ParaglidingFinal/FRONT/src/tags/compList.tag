<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="utg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ attribute name="finishedCompetitions" required="true" rtexprvalue="true"
              type="java.util.List<by.training.paragliding.bean.entity.Competition>"%>
<%@ attribute name="futureCompetitions" required="true" rtexprvalue="true"
              type="java.util.List<by.training.paragliding.bean.entity.Competition>"%>
<div class="container">
    <div class="card rounded-form">
        <div class="card-body col-12">
            <div class="list-group list-group-horizontal-sm" id="myList" role="tablist">
                <a class="list-group-item list-group-item-action active" data-toggle="list"
                   href="#finished"
                   role="tab"><fmt:message key="competitions.finished"/> </a>
                <a class="list-group-item list-group-item-action" data-toggle="list" href="#future"
                   role="tab"><fmt:message key="competitions.future"/></a>
            </div>
            <div class="tab-content">
                <div class="tab-pane active" id="finished" role="tabpanel">
                    <utg:competitionTable tableCompetitions="${finishedCompetitions}"/>
                </div>
                <div class="tab-pane" id="future" role="tabpanel">
                    <utg:competitionTable tableCompetitions="${futureCompetitions}"/>
                </div>
            </div>
        </div>
    </div>
</div>
