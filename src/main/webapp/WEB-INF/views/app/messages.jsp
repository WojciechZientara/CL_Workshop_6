<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">

    <c:forEach items="${messages}" var="message">
        <c:set var = "fullDate" value = "${message.created}"/>
        <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />

        <div class="tweet">
            <c:choose>
                <c:when test="${sessionScope.id == message.sender.id}">
                <span style="float: left; font-size: 25px; color: red"> &#11017; </span>
                    Do: <b><a href="${pageContext.request.contextPath}/app/userProfile/${message.recipient.id}">
                        ${message.recipient.firstName} ${message.recipient.lastName}
                </a></b><br>
                </c:when>
                <c:otherwise>
                    <span style="float: left; font-size: 25px; color: green"> &#11018;</span>
                    <c:if test="${!message.read}">
                        <span style="font-size: 25px; color: green">Nowa wiadomość!</span><br>
                    </c:if>

                    Od: <b><a href="${pageContext.request.contextPath}/app/userProfile/${message.sender.id}">
                            ${message.sender.firstName} ${message.sender.lastName}
                    </a></b><br>
                </c:otherwise>
            </c:choose>

            Data: <b>${shortDate}</b><br>
            <a style="color: black" href="${pageContext.request.contextPath}/app/messages/${message.id}">
                <c:choose>
                    <c:when test="${fn:length(message.text) >30}">
                        ${fn:substring(message.text, 0, 30)}<b>[...czytaj dalej]</b>
                    </c:when>
                    <c:otherwise>
                        ${message.text}
                    </c:otherwise>
                </c:choose>
            </a>
            <br>
        </div>

    </c:forEach>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

