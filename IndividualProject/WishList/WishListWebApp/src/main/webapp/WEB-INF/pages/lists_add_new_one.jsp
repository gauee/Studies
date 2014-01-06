<%-- 
    Document   : lists_add_new_one
    Created on : 2014-01-04, 22:07:18
    Author     : gauee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:head>
    <jsp:attribute name="pageTitle">
        Listy
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
        <h2>Tworzenie nowej listy</h2>
        <!--<a href="myLists_add_new_one" ><img src="resources/images/icons/add.ico"/>Stwórz nową listę</a>-->
        </jsp:attribute>
        <jsp:attribute name="content" >
            <div style="padding:0 0 40px 0"></div>
        <div id="form_container">
            <form id="form_769762" class="appnitro" method="post" action="myLists_add_new_one">
                <ul>
                    <li id="li_1" class="">
                        <label class="description" for="element_1">Nazwa listy </label>
                        <div>
                            <input id="element_1" name="list_name" class="element text medium" type="text" maxlength="255" value=""> 
                        </div> 
                    </li>
                    <li class="buttons">
                        <input type="hidden" name="form_id" value="769762">
                        <input id="saveForm" class="button_text" type="submit" name="submit" value="Stwórz listę">
                    </li>
                </ul>
            </form>	
        </div>
    </jsp:attribute>
</t:template>>
