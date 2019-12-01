<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="menu" class="mainFrame">
    <ul>
        <li><a href="${pageContext.request.contextPath}/app/main">Strona Główna</a></li>
        <li><a href="${pageContext.request.contextPath}/app/myTweets">Moje Posty</a></li>
        <li><a href="${pageContext.request.contextPath}/app/messages">Wiadomości</a></li>
        <li class="userProfile">
            <a href="${pageContext.request.contextPath}/app/userProfile">&#x1f464; ${sessionScope.userName}</a></li>
    </ul>
</div>