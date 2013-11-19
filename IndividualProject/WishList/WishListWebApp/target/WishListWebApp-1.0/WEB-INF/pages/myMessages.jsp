<%-- 
    Document   : index
    Created on : 2013-10-20, 20:46:05
    Author     : gauee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:head>
    <jsp:attribute name="pageTitle">
        Wiadomości
    </jsp:attribute>
</t:head>
<t:template>
    <jsp:attribute name="sideBar" >
        Hej SideBar!
    </jsp:attribute>
    <jsp:attribute name="content" >
        <h2>Message:</h2>
        <h4>${message}</h4>
    </jsp:attribute>
</t:template>>
