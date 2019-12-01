<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">
    <div id="tweetForm">

        <c:set var = "fullDate" value = "${message.created}"/>
        <c:set var = "shortDate" value = "${fn:substring(fullDate, 0, 10)}" />

        Od: ${message.sender.firstName} ${message.sender.lastName} <br>
        Do: ${message.recipient.firstName} ${message.recipient.lastName} <br>
        Data: ${shortDate} <br>
        ${message.text}
    </div>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

