<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">
    <div id="tweetForm">
        Od: ${message.sender.firstName} ${message.sender.lastName} <br>
        Do: ${message.recipient.firstName} ${message.recipient.lastName} <br>
        <form:form method="post" modelAttribute="message" >
        <form:hidden path="sender" value="${message.sender.id}" />
        <form:hidden path="recipient" value="${message.recipient.id}" />
        <form:textarea path="text" rows="4" cols="100" id="tweetArea"/>
            <form:errors path="text" cssClass="error"/><br>
        <input type="submit" value="Wyślij" class="submitBtn">
    </form:form>
    </div>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

