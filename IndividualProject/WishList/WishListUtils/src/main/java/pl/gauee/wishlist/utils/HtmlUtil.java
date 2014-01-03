/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils;

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
}
