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

    public static final String TAG_NEW_LINE = "<br />";

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

    public static String getBold(String toBold) {
        return new StringBuilder().append("<b>").append(toBold).append("</b>").toString();
    }

    public static String getAhrefLink(String href, String aName) {
        return new StringBuilder().append("<a href=\"")
                .append(href)
                .append("\" >")
                .append(aName)
                .append("</a>").toString();
    }

    public static String getImgSrc(String href) {
        return new StringBuilder()
                .append("<img src=\"")
                .append(href)
                .append("\" />")
                .toString();
    }

    public static String getImgSrc(String href, int width, int height) {
        String rawImg = getImgSrc(href);
        return new StringBuilder()
                .append(rawImg.substring(0, rawImg.length() - 2))
                .append("width=\"")
                .append(width)
                .append("px\" height=\"")
                .append(height)
                .append("px\" />")
                .toString();
    }

    public static String getNewLine() {
        return TAG_NEW_LINE;
    }

    public static String getCheckBox(String action, boolean checked) {
        return new StringBuilder().append("<input type=\"checkbox\" value=\"")
                .append(action)
                .append("\" ")
                .append(checked ? "checked" : "")
                .append(">").toString();
    }

    public static String getFormWithImageInput(String action, String method, String inputName, String inputImageSrc) {
        if (false) {
            return "<form action=\"myFriends\" method=\"POST\" >"
                    + "<input type=\"image\" name=\"friendToDelete\" src=\"/wishListWebApp/resources/images/icons/delete.ico\" alt=\"delete\">"
                    + "</form>";
        }

        return new StringBuilder()
                .append("<form action=\"")
                .append(action)
                .append("\" method=\"")
                .append(method)
                .append("\" > ")
                .append("<input type=\"image\" name=\"")
                .append(inputName)
                .append("\" src=\"")
                .append(inputImageSrc)
                .append("\" alt=\"delete\"> ")
                .append("</form>")
                .toString();
    }
}
