/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.html;

import java.util.LinkedList;
import java.util.List;
import pl.gauee.wishlist.utils.HtmlUtil;
import pl.gauee.wishlist.utils.IconUtils;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class MyFriendBuilder {

    private static final String[] friendsLabel = new String[]{
        "login", "imię", "nazwisko", "usuń"
    };

    public static String build(WishUser user) {
        StringBuilder sb = new StringBuilder();

        if (user.getUserFriends() == null || user.getUserFriends().isEmpty()) {
            sb.append("Nie masz jeszcze żadnych znajomych");
        } else {
            List<String> friendsNames = new LinkedList<String>();
            sb.append("<table>");
            sb.append(HtmlUtil.getTableRow(friendsLabel));
            for (WishUser friend : user.getUserFriends()) {
                friendsNames.add(HtmlUtil.getBold(friend.getLogin()) + " - " + friend.getName() + " " + friend.getSurname());
                sb.append(HtmlUtil.getTableRow(
                        friend.getLogin(),
                        friend.getName(),
                        friend.getSurname(),
                        getDeleteFriendIcon(friend.getLogin())));
            }
            sb.append("</table>");
        }

        return sb.toString();
    }

    private static String getDeleteFriendIcon(String userName) {
        return new StringBuilder()
                .append(HtmlUtil.getFormWithImageInput(PageUtils.MyFriendsDeleteFriendship + "?friendToDelete=" + userName, "POST", "friendToDelete", IconUtils.iconDelete))
                .toString();
    }
}
