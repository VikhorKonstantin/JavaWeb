<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<footer>
    <nav role="navigation" class="navbar navbar-fixed-bottom">
        <div class="row text-center text-xs-center text-sm-left text-md-left">

            <div class="col-xs-12 col-sm-4 col-md-4">
                <h3 class="footer_h3"><fmt:message key="footer.links"/></h3>
                <ul class="footer_ul">
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="${HomeRef}"><fmt:message
                            key="home.title"/></a></li>
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#"><fmt:message key="about"/></a></li>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4">
                <h3 class="footer_h3 fb">FAI</h3>
                <ul class="footer_ul">
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">FAI Rating</a></li>
                    <li><em class="fas fa-long-arrow-alt-right"></em><a href="#">FAI Main</a></li>
                </ul>
            </div>
            <div class="copyright">
                <div class="container">
                    <div class="row">
                        <p>© Copyright <ctg:info-time/>.<fmt:message key="copyright"/></p>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</footer>