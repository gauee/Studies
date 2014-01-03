<%-- 
Document   : head
Created on : 2013-10-25, 08:48:12
Author     : gauee
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="pageTitle" fragment="true"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WishList - <jsp:invoke fragment="pageTitle" /></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script type='text/javascript' src='http://dev.baczyk.net/wp-includes/js/jquery/jquery.js?ver=1.10.2'></script>
    <script type='text/javascript' src='http://dev.baczyk.net/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
</head>