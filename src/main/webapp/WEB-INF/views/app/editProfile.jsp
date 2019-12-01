<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
<%@include file="/WEB-INF/assets/menu.jsp"%>

<div id="main" class="mainFrame">
    <span id="login" class="landingPage">
        <table>
            <form:form method="post" modelAttribute="user" >
                <form:hidden path="id" value="${sessionScope.id}" />
                <tr><td>Imię: </td><td><form:input path="firstName" /></td></tr>
                <tr><td>        </td><td><form:errors path="firstName" cssClass="error"/></td></tr>
                <tr><td>Nazwisko: </td><td><form:input path="lastName" /></td></tr>
                <tr><td>        </td><td><form:errors path="lastName" cssClass="error"/></td></tr>
                <tr><td>E-mail: </td><td><form:input path="email" /></td></tr>
                <tr><td>        </td><td><form:errors path="email" cssClass="error"/></td></tr>
                <tr><td>Hasło: </td><td><form:password path="password" value=""/></td></tr>
                <tr><td></td><td><input type="submit" value="Zapisz" class="submitBtn"></td></tr>
            </form:form>
        </table>
    </span>
</div>

<%@include file="/WEB-INF/assets/footer.jsp"%>

