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
import pl.gauee.wishlist.utils.IconUtils;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishItemInList;
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

        if (list.getItems() == null || list.getItems().isEmpty()) {
            sb
                    .append("</table>")
                    .append("<p>")
                    .append("Niestety lista jest na chwilę obecną pusta.")
                    .append(HtmlUtil.TAG_NEW_LINE)
                    .append("Możesz dodać nowe artykuły poprzez edycje listy w innym oknie.")
                    .append(" </p> ");
            return sb.toString();
        }

        sb.append(HtmlUtil.getBold(HtmlUtil.getTableRow(itemsLables)));

        for (WishItemInList item : list.getItems()) {
            sb.append(HtmlUtil.getTableRow(
                    item.isBought() ? getBoughtLink() : getNotBoughtLink(),
                    item.getItem().getName(),
                    item.getItem().getDescription(),
                    item.getItem().getPrice() + "zł",
                    dateFormat.format(item.getLastUpdate()),
                    HtmlUtil.getImgSrc(item.getItem().getPhotoUrl() == null ? "" : item.getItem().getPhotoUrl())));
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

    public static String getBoughtLink() {
        return HtmlUtil.getAhrefLink(PageUtils.MyItemBoughtCancel,
                HtmlUtil.getImgSrc(IconUtils.iconBought));
    }

    public static String getNotBoughtLink() {
        return HtmlUtil.getAhrefLink(PageUtils.MyItemBought,
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
                .append("<form id=\"form_769762\" class=\"appnitro\" method=\"post\" action=\"")
                .append(PageUtils.MyListAddNewItem)
                .append("?listId=")
                .append(idAsParam)
                .append("\">")
                .append("<ul>")
                .append("<li id=\"li_1\">")
                .append("<label class=\"description\" for=\"element_1\">")
                .append("Produkt")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_1\" name=\"itemMainName\" class=\"element text medium\" type=\"text\" required>")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_2\">")
                .append("<label class=\"description\" for=\"element_2\">")
                .append("Nazwa")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_2\" name=\"itemName\" class=\"element text medium\" type=\"text\" required>")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_3\">")
                .append("<label class=\"description\" for=\"element_3\">")
                .append("Opis")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_3\" name=\"itemDescribe\" class=\"element text medium\" type=\"text\" >")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_4\">")
                .append("<label class=\"description\" for=\"element_4\">")
                .append("Cena")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_4\" name=\"itemPrice\" class=\"element text medium\" type=\"text\" >")
                .append("</div>")
                .append("</li>")
                .append("<li id=\"li_5\">")
                .append("<label class=\"description\" for=\"element_5\">")
                .append("Zdjęcie")
                .append("</label>")
                .append("<div>")
                .append("<input id=\"element_5\" name=\"itemPhoto\" class=\"element text medium\" type=\"file\" accept=\"image/*\" >")
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
                .append("<table>")
                .append(HtmlUtil.getTableRow("Produkt1", "Chleb"))
                .append(HtmlUtil.getTableRow("Produkt2", "Masło"))
                .append("</table>");


        return sb.toString();
    }
}
