<%-- 
    Document   : header
    Created on : 2013-10-24, 09:21:11
    Author     : gauee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <p>WishList 1.0</p>
    <nav>
        <a href="welcome" >Strona Główna</a>
        <!--<a href="myMessages" >Wiadomosci</a>-->
        <a href="myLists" >Listy</a>
        <a href="myFriends" >Znajomi</a>
        <a href="mySite" >O mnie</a>
        <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </nav>
</header>
