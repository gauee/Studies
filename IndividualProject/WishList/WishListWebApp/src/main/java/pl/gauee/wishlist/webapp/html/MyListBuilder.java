/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.html;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import pl.gauee.wishlist.utils.HtmlUtil;
import pl.gauee.wishlist.utils.HttpAction;
import pl.gauee.wishlist.utils.IconUtils;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class MyListBuilder {

    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
    private static final String[] itemsLables = new String[]{
        "kupiony",
        "nazwa artykułu",
        "opis",
        "cena",
        "ostatnia zmiana",
        "zdjęcie",
        "",
        ""
    };

    public static String buildViewOfAllList(List<WishList> lists) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Moje listy"))
                .append("<table>");
        for (WishList list : lists) {
            sb.append(HtmlUtil.getTableRow(
                    list.getName(),
                    dateFormat.format(list.getCreatedDate()),
                    getImgA(PageUtils.MyListPreview + getListIdAsParam(list), IconUtils.iconPreview),
                    getImgA(PageUtils.MyListEdit + getListIdAsParam(list), IconUtils.iconEdit),
                    getImgA(PageUtils.MyListShare + getListIdAsParam(list), IconUtils.iconShare),
                    getImgA(PageUtils.MyListDelete + getListIdAsParam(list), IconUtils.iconDelete)));
        }
        sb.append("</table>");
        return sb.toString();
    }

    private static String getImgA(String pageHref, String imageHref) {
        return HtmlUtil.getAhrefLink(pageHref, HtmlUtil.getImgSrc(imageHref));
    }

    public static Object buildViewOneList(WishList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Podgląd listy"))
                .append("Lista: ")
                .append(HtmlUtil.getBold(list.getName()))
                .append("  stworzona: ")
                .append(dateFormat.format(list.getCreatedDate()))
                .append(HtmlUtil.getNewLine())
                .append("<table>");

        if (list.getListItems() == null || list.getListItems().isEmpty()) {
            return createPageForEmptyList(sb, list.getId());
        }

        sb.append(HtmlUtil.getBold(HtmlUtil.getTableRow(itemsLables)));

        for (WishItem item : list.getListItems()) {
            sb.append(HtmlUtil.getTableRow(
                    item.isBought() ? getBoughtLink(list.getId(), item.getId()) : getNotBoughtLink(list.getId(), item.getId()),
                    item.getName(),
                    item.getDescription(),
                    item.getPrice() + "zł",
                    dateFormat.format(item.getLastUpdate()),
                    HtmlUtil.getImgSrc(item.getPhotoUrl() == null ? "" : item.getPhotoUrl())));
        }

        sb.append("</table>");
        return sb.toString();
    }

    public static String buildViewSharedList(Set<WishUser> friends, WishList list) {
        StringBuilder sb = new StringBuilder();

        if (friends == null || friends.isEmpty()) {
            return new StringBuilder().append("Nie masz znajomych aby współdzielić listę.").toString();
        }

        sb.append("Współdzielenie listy: ")
                .append(list.getName());

        sb.append(buildViewFriendsSharedList(friends, list));
        sb.append(buildViewFriendsNotSharedList(friends, list));

        return sb.toString();
    }

    public static String getBoughtLink(long listId, long itemId) {
        return HtmlUtil.getAhrefLink(PageUtils.MyItemBoughtCancel + "?listId=" + listId + "&itemId=" + itemId,
                HtmlUtil.getImgSrc(IconUtils.iconBought));
    }

    public static String getNotBoughtLink(long listId, long itemId) {
        return HtmlUtil.getAhrefLink(PageUtils.MyItemBought + "?listId=" + listId + "&itemId=" + itemId,
                HtmlUtil.getImgSrc(IconUtils.iconNotBought));
    }

    private static String getListIdAsParam(WishList list) {
        return "?listId=" + list.getId();
    }

    private static String buildViewFriendsSharedList(Set<WishUser> friends, WishList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Znajomi współdzielący listę:"))
                .append(HtmlUtil.TAG_NEW_LINE)
                .append("<table>");
        for (WishUser friend : friends) {
            if (friend.getUserLists().contains(list)) {
                sb.append(HtmlUtil.getTableRow(
                        friend.getLogin(),
                        HtmlUtil.getFormWithImageInput(PageUtils.MyListDeleteShare + "?listId=" + list.getId() + "&friendLogin=" + friend.getLogin(), "POST", "test", IconUtils.iconDeleteShareList)));
            }
        }


        sb.append("</table>");

        return sb.toString();
    }

    private static String buildViewFriendsNotSharedList(Set<WishUser> friends, WishList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Znajomi jeszcze nie współdzielący listę:"))
                .append(HtmlUtil.TAG_NEW_LINE)
                .append("<table>");
        for (WishUser friend : friends) {
            if (!friend.getUserLists().contains(list)) {
                sb.append(HtmlUtil.getTableRow(
                        friend.getLogin(),
                        HtmlUtil.getFormWithImageInput(PageUtils.MyListAddShare + "?listId=" + list.getId() + "&friendLogin=" + friend.getLogin(), "POST", "test", IconUtils.iconAddShareList)));
            }
        }


        sb.append("</table>");



        return sb.toString();
    }

    public static String buildViewForListEdit(WishList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Edycja listy: " + list.getName()))
                .append(HtmlUtil.TAG_NEW_LINE)
                //                .append(getAddNewItemToListForm(getListIdAsParam(list)))
                .append(getAddNewItemToListForm(list.getId()))
                .append(getEditableItemListList(list));

        return sb.toString();
    }

//    private static Object getAddNewItemToListForm(String idAsParam) {
    private static String getAddNewItemToListForm(long idAsParam) {
        StringBuilder sb = new StringBuilder();

        sb
                .append("<div id=\"form_container\">")
                .append("<form id=\"form_769762\" class=\"appnitro\" method=\"post\" enctype=\"multipart/form-data\" action=\"")
                .append(PageUtils.MyListAddNewItem)
                .append("?listId=")
                .append(idAsParam)
                .append("\" >")
                .append("<ul>")
                .append("</li>")
                .append("<li id=\"li_1\">")
                .append("<label class=\"description\" for=\"element_1\">")
                .append("Nazwa")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_1\" name=\"itemName\" class=\"element text medium\" type=\"text\" required>")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_2\">")
                .append("<label class=\"description\" for=\"element_2\">")
                .append("Opis")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_2\" name=\"itemDescribe\" class=\"element text medium\" type=\"text\" >")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_3\">")
                .append("<label class=\"description\" for=\"element_3\">")
                .append("Cena")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_3\" name=\"itemPrice\" class=\"element text medium\" type=\"text\" >")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_4\">")
                .append("<label class=\"description\" for=\"element_4\">")
                .append("Zdjęcie")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_4\" name=\"itemPhoto\" class=\"element text medium\" type=\"file\" accept=\"image/*\" >")
                .append("</div>")
                .append("</li>")
                .append("<li class=\"buttons\">")
                .append("<input type=\"hidden\" name=\"form_id\" value=\"769762\">")
                .append("<input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Dodaj Artykuł\">")
                .append("</li>")
                .append("</ul>")
                .append("</form>")
                .append("</div>");

        return sb.toString();
    }

    private static Object getEditableItemListList(WishList list) {
        StringBuilder sb = new StringBuilder();

        sb
                .append(HtmlUtil.getHeader3("Lista artykułów:"))
                .append("<table>");
        for (WishItem item : list.getListItems()) {
            sb.append(HtmlUtil.getTableRow(
                    item.getName(),
                    item.getDescription(),
                    item.getPrice() + "zł",
                    item.getLastUpdate().toString(),
                    HtmlUtil.getImgSrc(item.getPhotoUrl(), 50, 50),
                    HtmlUtil.getFormWithImageInput(getDeleteItemAction(item.getId(), list.getId()), HttpAction.POST.toString(), "deleteItem", IconUtils.iconDelete)));
        }

        sb
                .append("</table>");


        return sb.toString();
    }

    private static String getDeleteItemAction(long itemId, long listId) {
        return PageUtils.MyItemDelete + "?itemId=" + itemId + "&listId=" + listId;
    }

    private static Object createPageForEmptyList(StringBuilder sb, long listId) {
        return sb
                .append("</table>")
                .append("<p>")
                .append("Niestety lista jest na chwilę obecną pusta.")
                .append(HtmlUtil.TAG_NEW_LINE)
                .append("Możesz dodać nowe artykuły poprzez edycje listy w innym oknie.")
                .append(" </p> ")
                .append(HtmlUtil.getAhrefLink(PageUtils.MyListEdit + "?listId=" + listId, "Przejdz do edycji listy"))
                .toString();
    }
}
