<%-- 
    Document   : index
    Created on : 2013-10-20, 20:46:05
    Author     : gauee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:head>
    <jsp:attribute name="pageTitle">
        Znajoma
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

    </jsp:attribute>
    <jsp:attribute name="content" >
        <h3>Dodaj znajomego</h3>
        <form action="#">
            Szukaj: <input type="text" id="friendSearcher" autocomplete="off" name="friendSearcher" >
            <input type="submit" value="Dodaj" >
        </form>
        <h2>Message:</h2>
        <h4>${message}</h4>
        <script>
            $(function() {
                $("#friendSearcher").autocomplete({
                    source: ${nonFriendList}
                });
            });
        </script> 
    </jsp:attribute>


</t:template>
