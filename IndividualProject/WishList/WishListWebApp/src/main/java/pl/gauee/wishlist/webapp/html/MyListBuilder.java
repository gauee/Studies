/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.html;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import pl.gauee.wishlist.utils.HtmlUtil;
import pl.gauee.wishlist.utils.IconUtils;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishItemInList;
import pl.gauee.wishlist.utils.persistance.WishList;

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
                    getImgA(PageUtils.MyListPreview, IconUtils.iconPreview),
                    getImgA(PageUtils.MyListEdit, IconUtils.iconEdit),
                    getImgA(PageUtils.MyListShare, IconUtils.iconShare),
                    getImgA(PageUtils.MyListDelete, IconUtils.iconDelete)));
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

        sb.append(HtmlUtil.getBold(HtmlUtil.getTableRow(itemsLables)));

        for (WishItemInList item : list.getItems()) {
            sb.append(HtmlUtil.getTableRow(
                    item.isBought()? getBoughtLink() : getNotBoughtLink(),
                    item.getItem().getName(),
                    item.getItem().getDescription(),
                    item.getItem().getPrice() + "zł",
                    dateFormat.format(item.getLastUpdate()),
                    HtmlUtil.getImgSrc(item.getItem().getPhotoUrl()== null ? "" : item.getItem().getPhotoUrl())));
        }

        sb.append("</table>");
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
}
