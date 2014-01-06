/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.html;

import pl.gauee.wishlist.utils.HtmlUtil;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class MySiteBuilder {

    private static final String titlePage = "Dane użytkownika";
    private static final String titleStatistics = "Statystyki";
    private static final String labelLogin = "Login: ";
    private static final String labelName = "Imie: ";
    private static final String labelSurname = "Nazwisko: ";
    private static final String labelEmail = "E-mail: ";
    private static final String labelMsisdn = "Komórka: ";

    public static String build(WishUser user) {
        return new StringBuilder()
                .append(HtmlUtil.getHeader3(titlePage))
                .append("<table>")
                .append(HtmlUtil.getTableRow(labelLogin, user.getLogin()))
                .append(HtmlUtil.getTableRow(labelName, user.getName()))
                .append(HtmlUtil.getTableRow(labelSurname, user.getSurname()))
                .append(HtmlUtil.getTableRow(labelEmail, user.getEmail()))
                .append(HtmlUtil.getTableRow(labelMsisdn, user.getMsisdn()))
                .append("</table>")
                .append(HtmlUtil.getHeader3(titleStatistics))
                .append("<table>")
                .append(HtmlUtil.getTableRow(
                "Posiadanych znajomych:",
                user.getUserFriends().size() + ""))
                .append(HtmlUtil.getTableRow(
                "Prowadzonych list:",
                user.getUserLists().size() + ""))
                .append("</table>")
                .toString();
    }
}
