/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.html;

import java.util.LinkedList;
import java.util.List;
import pl.gauee.wishlist.utils.HtmlUtil;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class MyFriendBuilder {

    public static String build(WishUser user) {
        StringBuilder sb = new StringBuilder();

        if (user.getUserFriends()== null || user.getUserFriends().isEmpty()) {
            sb.append("Nie masz jeszcze żadnych znajomych");
        } else {
            List<String> friendsNames = new LinkedList<String>();
            for (WishUser friend : user.getUserFriends()) {
                friendsNames.add(HtmlUtil.getBold(friend.getLogin()) + " - " + friend.getName() + " " + friend.getSurname());
            }
            sb.append(HtmlUtil.createUlList(friendsNames));
        }

        return sb.toString();
    }
}
