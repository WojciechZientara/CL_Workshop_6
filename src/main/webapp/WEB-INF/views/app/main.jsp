<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">

    <div id="tweetForm">
        Dodaj nowy Post: <form:form method="post" modelAttribute="tweet" >
        <form:hidden path="user" value="${sessionScope.id}" />
        <form:textarea path="text" rows="2" cols="70" id="tweetArea"/>
            <form:errors path="text" cssClass="error"/><br>
        <input type="submit" value="Wyślij" class="submitBtn">
    </form:form>
    </div>

    <br>

    <c:forEach items="${tweets}" var="tweet">
    <c:set var = "fullDate" value = "${tweet.created}"/>
    <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />

        <div class="tweet">
            <b>
                <a href="${pageContext.request.contextPath}/app/userProfile/${tweet.user.id}">
                        ${tweet.user.firstName} ${tweet.user.lastName}
                </a><br>
                    ${shortDate}<br>
            </b>
                ${tweet.text}<br><br>

            <div class="comments">
                Dodaj komentarz:
                <form:form method="post" action="${pageContext.request.contextPath}/app/main/comments" modelAttribute="comment" >
                <form:hidden path="user" value="${sessionScope.id}" />
                <form:hidden path="tweet" value="${tweet.id}" />
                <form:textarea path="text" rows="1" cols="60"/><br>
                    <c:if test="${tweet.id == err}">
                        <span class="error">Ilość znaków musi wynosić między 1 a 60.</span><br>
                    </c:if>
<%--                <form:errors path="text" cssClass="error"/>--%>
                <input type="submit" value="Skomentuj" class="submitBtn">
            </form:form>
                <br>
                Komentarze:<br><br>

                <c:forEach items="${tweet.comments}" var="comment">
                    <c:set var = "fullDate" value = "${comment.created}"/>
                    <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />

                    <b>${shortDate} <br>
                        <a href="${pageContext.request.contextPath}/app/userProfile/${comment.user.id}">
                                ${comment.user.firstName} ${comment.user.lastName}
                        </a></b><br>
                    ${comment.text}<br><br>

                </c:forEach>

            </div>
        </div>

    </c:forEach>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

