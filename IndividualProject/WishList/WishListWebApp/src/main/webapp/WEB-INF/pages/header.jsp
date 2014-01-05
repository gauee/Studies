<%-- 
    Document   : header
    Created on : 2013-10-24, 09:21:11
    Author     : gauee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <p>WishList 1.0</p>
    <nav>
        <a href="welcome" >Strona Główna</a>
        <%--<c:if test="${not empty userName}">--%>
        <sec:authorize access="hasRole('ROLE_USER')">
            <a href="myLists" >Listy</a>
            <a href="myFriends" >Znajomi</a>
            <a href="mySite" >O mnie</a>
            <a href="<c:url value="/j_spring_security_logout" />" > Wyloguj, <sec:authentication property="principal"/> </a>
        </sec:authorize>
        <%--</c:if>--%>
    </nav>
</header>
