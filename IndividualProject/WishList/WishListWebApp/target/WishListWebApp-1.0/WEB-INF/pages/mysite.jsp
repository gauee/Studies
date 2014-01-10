<%-- 
    Document   : index
    Created on : 2013-10-20, 20:46:05
    Author     : gauee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:head>
    <jsp:attribute name="pageTitle">
        O mnie
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
        <h2>Twoje konto</h2>
    </jsp:attribute>
    <jsp:attribute name="content" >
        <h4>${message}</h4>
    </jsp:attribute>
</t:template>>
