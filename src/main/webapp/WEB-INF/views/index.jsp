<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/WEB-INF/assets/header.jsp"%>
    <div id="main" class="mainFrame">
        <span id="welcomeText" class="landingPage">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Fusce in convallis ipsum, et luctus massa. Nam id venenatis mi, vel volutpat nibh.
            Integer hendrerit tortor molestie semper posuere. Suspendisse sed euismod eros.
            Nunc mollis leo eget neque ultricies imperdiet. Morbi nec ex vitae quam volutpat elementum quis et neque.
            Sed vel sem purus. Nulla tincidunt fermentum ante, ut consectetur sem pretium vitae.
            Sed fringilla orci vitae risus pretium vulputate. Nunc suscipit laoreet libero quis condimentum.
            Aliquam porttitor sed mi lobortis vulputate.
        </span>
        <span id="login" class="landingPage">
            Dane użytkownika:<br><br>
            <form method="post">
                <table>
                    <tr><td>E-mail: </td><td><input type="text" name="email" /></td></tr>
                    <tr><td>Hasło: </td><td><input type="password" name="password" /></td></tr>
                    <tr><td></td><td>
                        <c:if test="${incorrectCredentials == true}">
                            <span class="error">Błędny login lub hasło</span>
                        </c:if>
                    </td></tr>
                    <tr><td></td><td><input type="submit" value="Zaloguj się" class="submitBtn"></td></tr>
                </table>
            </form>
            <br><br>
            Nie masz konta? <a href="${pageContext.request.contextPath}/register">Zarejestruj się!</a>
        </span>
    </div>
<%@include file="/WEB-INF/assets/footer.jsp"%>

