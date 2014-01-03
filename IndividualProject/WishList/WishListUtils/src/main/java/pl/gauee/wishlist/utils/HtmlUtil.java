/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils;

import java.util.List;

/**
 *
 * @author gauee
 */
public class HtmlUtil {

    public static String getHeader3(String msg) {
        return new StringBuilder().append("<h3>").append(msg).append("</h3>").toString();
    }

    public static String getTableRow(String... cols) {

        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
        for (String col : cols) {
            sb.append("<td>")
                    .append(col)
                    .append("</td>");
        }
        sb.append("</tr>");

        return sb.toString();
    }

    public static String createUlList(List<String> positions) {
        StringBuilder sb = new StringBuilder();

        sb.append("<ul>");
        for (String pos : positions) {
            sb.append("<li>")
                    .append(pos)
                    .append("</li>");
        }
        sb.append("</ul>");

        return sb.toString();
    }

    public static String getBold(String login) {
        return new StringBuilder().append("<b>").append(login).append("</b>").toString();
    }
}
