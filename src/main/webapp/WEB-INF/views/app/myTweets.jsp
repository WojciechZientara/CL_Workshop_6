<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">

    <c:forEach items="${tweets}" var="tweet">
    <c:set var = "fullDate" value = "${tweet.created}"/>
    <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />

        <div class="myTweet">
            <div style="width: 80%">
                <b> ${tweet.user.firstName} ${tweet.user.lastName}<br>
                ${shortDate}<br></b>
                ${tweet.text}
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/app/myTweets/update/${tweet.id}">Edytuj</a><br>
                <a href="${pageContext.request.contextPath}/app/myTweets/delete/${tweet.id}"
                    onclick="confirm('Czy na pewno chcesz usunąć wpis?')">Usuń</a><br>
            </div>
            <br>
            <div class="comments">
                Komentarze:<br><br>

                <c:forEach items="${tweet.comments}" var="comment">
                    <c:set var = "fullDate" value = "${comment.created}"/>
                    <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />

                    <b>${shortDate} <br> ${comment.user.firstName} ${comment.user.lastName}</b><br>
                    ${comment.text}<br><br>

                </c:forEach>
            </div>

        </div>

    </c:forEach>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

