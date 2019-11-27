<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Texter</title>
    <style> <%@include file="/WEB-INF/assets/style.css"%> </style>
</head>
<body>
<div id="logo" class="mainFrame">

    <c:choose>
        <c:when test="${sessionScope.logged == 'true'}">
            <a href="${pageContext.request.contextPath}/app/main">Texter</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/">Texter</a>
        </c:otherwise>
    </c:choose>

</div>
