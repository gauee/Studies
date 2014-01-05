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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/view.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">

    <script type='text/javascript' src='http://dev.baczyk.net/wp-includes/js/jquery/jquery.js?ver=1.10.2'></script>
    <script type='text/javascript' src='http://dev.baczyk.net/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    
</head>