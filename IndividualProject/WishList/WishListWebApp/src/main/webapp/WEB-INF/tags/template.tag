<%-- 
    Document   : template
    Created on : 2013-10-24, 09:07:29
    Author     : gauee
--%>

<%@tag description="Template of site" pageEncoding="UTF-8"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="content" fragment="true"%>
<%@attribute name="sideBar" fragment="true"%>

<%-- 
    Document   : index
    Created on : 2013-10-20, 20:46:05
    Author     : gauee
--%>
<html>
    <jsp:invoke fragment="header" />
    <body>
        <section id="content">
            <section id="main_content">
                <aside>
                    <jsp:invoke fragment="sideBar" />
                </aside>
                <section id="content_text">
                    <jsp:invoke fragment="content" />
                </section>
            </section>
        </section>
        <jsp:invoke fragment="footer" />
    </body>
</html>

