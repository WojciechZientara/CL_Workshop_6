<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">
    <div id="tweetForm">
        Edytuj Tweet: <form:form method="post" modelAttribute="tweet" >
        <form:hidden path="user" value="${sessionScope.id}" />
        <form:textarea path="text" rows="2" cols="70" id="tweetArea"/>
            <form:errors path="text" cssClass="error"/><br>
        <input type="submit" value="Edytuj" class="submitBtn">
    </form:form>
    </div>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

