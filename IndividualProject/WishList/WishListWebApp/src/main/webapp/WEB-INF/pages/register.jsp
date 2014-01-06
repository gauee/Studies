<%-- 
    Document   : register
    Created on : 2014-01-04, 12:02:59
    Author     : gauee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:head>
    <jsp:attribute name="pageTitle">
        Rejestracja
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
        <h2>Rejestracja</h2>
    </jsp:attribute>
    <jsp:attribute name="content" >
        <div style="height: 20px"></div>
        <div id="form_container">
            <form id="form_769762" class="appnitro" method="post" action="register">
                <ul>
                    <li id="li_1" class="">
                        <label class="description" for="element_1">Login </label>
                        <div>
                            <input id="element_1" name="reg_login" class="element text medium" type="text" maxlength="255" value=""> 
                        </div> 
                    </li>		<li id="li_4">
                        <label class="description" for="element_4">Name </label>
                        <span>
                            <input id="reg_name" name="reg_name" class="element text" maxlength="255" size="8" value="">
                            <label>First</label>
                        </span>
                        <span>
                            <input id="element_4_2" name="reg_surname" class="element text" maxlength="255" size="14" value="">
                            <label>Last</label>
                        </span> 
                    </li>		<li id="li_2">
                        <label class="description" for="element_2">Password </label>
                        <div>
                            <input id="element_2" name="reg_pass" class="element text medium" type="password" maxlength="255" value=""> 
                        </div> 
                    </li>		<li id="li_3">
                        <label class="description" for="element_3">Password confirm
                        </label>
                        <div>
                            <input id="element_3" name="reg_pass_conf" class="element text medium" type="password" maxlength="255" value=""> 
                        </div> 
                    </li>		<li id="li_5">
                        <label class="description" for="element_5">Email </label>
                        <div>
                            <input id="element_5" name="reg_email" class="element text medium" type="email" maxlength="255" value=""> 
                        </div> 
                    </li>		<li id="li_6" class="">
                        <label class="description" for="element_6">Phone </label>
                        <span>
                            <input id="element_6_1" name="reg_phone" class="element text" size="9" maxlength="9" value="" type="tel">
                            <label for="element_6_1">###-###-###</label>
                        </span>
                    </li>
                    <li class="buttons">
                        <input type="hidden" name="form_id" value="769762">

                        <input id="saveForm" class="button_text" type="submit" name="submit" value="Submit">
                    </li>
                </ul>
            </form>	

        </div>
    </jsp:attribute>
</t:template>>
