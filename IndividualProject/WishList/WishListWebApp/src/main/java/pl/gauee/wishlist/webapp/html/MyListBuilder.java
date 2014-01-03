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
import pl.gauee.wishlist.utils.decorators.DWishItemInList;
import pl.gauee.wishlist.utils.decorators.DWishList;

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

    public static String buildViewOfAllList(List<DWishList> lists) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Moje listy"))
                .append("<table>");
        for (DWishList list : lists) {
            sb.append(HtmlUtil.getTableRow(
                    list.getName(),
                    dateFormat.format(list.getCreatedTime()),
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

    public static Object buildViewOneList(DWishList list) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlUtil.getHeader3("Podgląd listy"))
                .append("Lista: ")
                .append(HtmlUtil.getBold(list.getName()))
                .append("  stworzona: ")
                .append(dateFormat.format(list.getCreatedTime()))
                .append(HtmlUtil.getNewLine())
                .append("<table>");

        sb.append(HtmlUtil.getBold(HtmlUtil.getTableRow(itemsLables)));

        for (DWishItemInList item : list.getItems()) {
            sb.append(HtmlUtil.getTableRow(
                    item.isBougth() ? getBoughtLink() : getNotBoughtLink(),
                    item.getName(),
                    item.getDescription(),
                    item.getPrice() + "zł",
                    dateFormat.format(item.getLastUpdate()),
                    HtmlUtil.getImgSrc(item.getPhoto() == null ? "" : item.getPhoto())));
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
