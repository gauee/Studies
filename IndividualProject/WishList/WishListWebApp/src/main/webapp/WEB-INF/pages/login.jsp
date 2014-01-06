<%-- 
    Document   : login
    Created on : 2013-10-25, 10:58:18
    Author     : gauee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:head>
    <jsp:attribute name="pageTitle">
        Logowanie
    </jsp:attribute>
</t:head>
<t:template>

    <jsp:attribute name="header">
        <jsp:include page="header.jsp" />
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="footer.jsp" />
    </jsp:attribute>

    <jsp:attribute name="sideBar" >
        <h3>Panel logowania</h3>
        <form name='f' action="<c:url value='j_spring_security_check' />"
              method='POST'>

            <table>
                <tr>
                    <td>User:</td>
                    <td><input type='text' name='j_username' value=''>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='j_password' />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit"
                                           value="submit" />
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input name="reset" type="reset" />
                    </td>
                </tr>
            </table>
        </form>
        <a href="register">
            <input type="button" value="Register" />
        </a>
    </jsp:attribute>
    <jsp:attribute name="content" >
        <h2>Witaj w aplikacji WishList'a</h2>
        <div class="errorblock">
            <p>
                Aby skorzystać z aplikacji wymagana jest identyfikacja użytkownika.
                Prosimy o zalogowanie się na swoje konto.
                Jeśli takiego nie posiadasz możesz darmowo stworzyć.
            </p>
        </div>
    </jsp:attribute>
</t:template>