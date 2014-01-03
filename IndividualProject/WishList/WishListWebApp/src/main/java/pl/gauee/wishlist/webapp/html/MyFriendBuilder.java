/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.html;

import java.util.LinkedList;
import java.util.List;
import pl.gauee.wishlist.utils.HtmlUtil;
import pl.gauee.wishlist.utils.decorators.DWishUser;

/**
 *
 * @author gauee
 */
public class MyFriendBuilder {

    public static String build(DWishUser user) {
        StringBuilder sb = new StringBuilder();

        if (user.getFriends() == null || user.getFriends().isEmpty()) {
            sb.append("Nie masz jeszcze żadnych znajomych");
        } else {
            List<String> friendsNames = new LinkedList<String>();
            for (DWishUser friend : user.getFriends()) {
                friendsNames.add(HtmlUtil.getBold(friend.getLogin()) + " - " + friend.getName() + " " + friend.getSurname());
            }
            sb.append(HtmlUtil.createUlList(friendsNames));
        }

        return sb.toString();
    }
}
