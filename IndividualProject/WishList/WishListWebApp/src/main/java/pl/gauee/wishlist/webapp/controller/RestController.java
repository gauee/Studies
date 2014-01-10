/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.rest.RestUserLists;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import pl.gauee.wishlist.utils.persistance.rest.RestWishListWithItems;
import pl.gauee.wishlist.utils.persistance.rest.RestWishUser;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;

/**
 *
 * @author gauee
 */
@Controller
//@RequestMapping(PageUtils.rest)
public class RestController {

    private final Logger logger = Logger.getLogger(RestController.class);
    @Autowired
    @Qualifier(value = "proxyBean")
    private RemoteAccessApi remoteAccessApi;

    @RequestMapping(value = PageUtils.restUserAuth, method = RequestMethod.GET)
    @ResponseBody
    public boolean isAuthenticated() {
        return true;
    }

    @RequestMapping(value = PageUtils.restUserMe, method = RequestMethod.GET)
    @ResponseBody
    public RestWishUser getMineUser() {
        logger.info("Logged user is " + getLoginCurrentLoggedUser());
        return new RestWishUser().packTo(
                remoteAccessApi.getUserByLogin(getLoginCurrentLoggedUser()));
    }

    @RequestMapping(value = PageUtils.restUserMyLists, method = RequestMethod.GET)
    @ResponseBody
    public RestUserLists getMyLists() {
        logger.info("Logged user is " + getLoginCurrentLoggedUser());
        return new RestUserLists(new RestWishList().packListTo(
                remoteAccessApi.getUserByLogin(getLoginCurrentLoggedUser()).getUserLists()));
    }

    @RequestMapping(value = PageUtils.restUserMyOneList, method = RequestMethod.GET)
    @ResponseBody
    public RestWishListWithItems getMyOneList(
            @RequestParam("listId") long listId) {

        List<WishList> lists = remoteAccessApi.getListOwnToUser(getLoginCurrentLoggedUser());
        RestWishListWithItems listWithItems = new RestWishListWithItems();
        for (WishList list : lists) {
            if (list.getId() == listId) {
                listWithItems.setList(new RestWishList().packTo(list));
                listWithItems.setItems(new RestWishItem().packListTo(list.getListItems()));
            }
        }
        return listWithItems;
    }

    @RequestMapping(value = PageUtils.restUserAddNewList, method = RequestMethod.GET)
    @ResponseBody
    public boolean addNewList(
            @RequestParam("listName") String listName) {

        
        logger.info("addNewList: " + listName);
        WishList list = new WishList(listName);
        return remoteAccessApi.addListToUser(list, getLoginCurrentLoggedUser());
        
    }

    private String getLoginCurrentLoggedUser() {
//        if (true) {
//            return (String) "gauee";
//        }
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
