/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gauee.wishlist.utils.CustomRequestParam;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishItemInList;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;
import pl.gauee.wishlist.webapp.api.WebListApi;
import pl.gauee.wishlist.webapp.api.WebUserApi;
import pl.gauee.wishlist.webapp.html.MyFriendBuilder;
import pl.gauee.wishlist.webapp.html.MyListBuilder;
import pl.gauee.wishlist.webapp.html.MySiteBuilder;

/**
 *
 * @author gauee
 */
@Controller
//@RequestMapping("/")
public class MainController {

    private final Logger logger = Logger.getLogger(MainController.class);
    @Autowired
    @Qualifier(value = "userApiBean")
    WebUserApi userApi;
    @Autowired
    @Qualifier(value = "listApiBean")
    WebListApi listApi;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        setUserNameCurrentLoggedUser(model);

        model.addAttribute("message", "Testuje servlet...");

        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        setUserNameCurrentLoggedUser(model);
        model.addAttribute("message", "Witaj na mojej pierwszej stronie napisanej w springu");

        return "index";
    }

    @RequestMapping(value = "/mySite", method = RequestMethod.GET)
    public String mySite(ModelMap model) {
        setUserNameCurrentLoggedUser(model);
        String currentLoggedUser = getLoginCurrentLoggedUser();
        WishUser user = userApi.getUserByLogin(currentLoggedUser);

        model.addAttribute("message", MySiteBuilder.build(user));

        return "mysite";
    }

    @RequestMapping(value = "/myMessages", method = RequestMethod.GET)
    public String myMessages(ModelMap model) {
        setUserNameCurrentLoggedUser(model);
        model.addAttribute("message", "Moje wiadomości");

        return "myMessages";
    }

    @RequestMapping(value = PageUtils.MyFriends, method = RequestMethod.GET)
    public String myFriends(ModelMap model) {
        setUserNameCurrentLoggedUser(model);
        WishUser user = userApi.getUserByLogin(getLoginCurrentLoggedUser());
//        logger.info(""+user.getUserFriends());

        List<String> nonFriendsLoginLists = userApi.getNonFriendsLoginForUser(getLoginCurrentLoggedUser());

        model.addAttribute("message", MyFriendBuilder.build(user));
        model.addAttribute("nonFriendList", nonFriendsLoginLists);
        return "friends";
    }

    @RequestMapping(value = PageUtils.MyFriendsAddNewOne, method = RequestMethod.POST)
    public String myFriendsAddNewOne(
            @RequestParam("friend_newOne") String friendToAdd,
            ModelMap model) {
        setUserNameCurrentLoggedUser(model);
        WishUser user = userApi.getUserByLogin(getLoginCurrentLoggedUser());
        logger.info("User to add is : " + friendToAdd);
        WishUser userToAdd = userApi.getUserByLogin(friendToAdd);
        userApi.joinTwoUserAsFriends(user, userToAdd);
        model.addAttribute("message", "User " + friendToAdd + " successful add to your friends");

        return getRedirectTo(PageUtils.MyFriends);
    }

    @RequestMapping(value = PageUtils.MyFriendsDeleteFriendship, method = RequestMethod.POST)
    public String myFriendsDeleteFriendship(
            @RequestParam("friendToDelete") String friendToDelete,
            ModelMap model) {
        WishUser user = userApi.getUserByLogin(getLoginCurrentLoggedUser());
        logger.info("User to delete is : " + friendToDelete);
        WishUser userToAdd = userApi.getUserByLogin(friendToDelete);
        userApi.deleteFriendship(user, userToAdd);
        model.addAttribute("message", "User " + friendToDelete + " successful delete from your friends");

        return getRedirectTo(PageUtils.MyFriends);
    }

    @RequestMapping(value = PageUtils.MyList, method = RequestMethod.GET)
    public String myLists(ModelMap model) {

        List<WishList> lists = listApi.getListsOwnedToUser(getLoginCurrentLoggedUser());

        model.addAttribute("message", MyListBuilder.buildViewOfAllList(lists));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListPreview, method = RequestMethod.GET)
    public String myListsPreview(
            @RequestParam("listId") long listId,
            ModelMap model) {
        WishList list = listApi.getListById(listId);
        model.addAttribute("message", MyListBuilder.buildViewOneList(list));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListEdit, method = RequestMethod.GET)
    public String myListsEdit(
            @RequestParam("listId") long listId,
            ModelMap model) {

        WishList list = listApi.getListById(listId);
        model.addAttribute("message", MyListBuilder.buildViewForListEdit(list));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListAddNewItem, method = RequestMethod.POST)
    public String myListsAddNewItem(
            @RequestParam("listId") long listId,
            @RequestParam(value = "itemMainName", required = true) String itemMainName,
            @RequestParam(value = "itemName", required = true) String itemName,
            @RequestParam(value = "itemDescribe", required = false) String itemDescribe,
            @RequestParam(value = "itemPrice", required = false) String itemPrice,
            @RequestParam(value = "itemPhoto", required = false) String itemPhotoUrl,
            ModelMap model) {

        StringBuilder sb = new StringBuilder();
        sb
                .append("Post form with attributes: ")
                .append("\n listId: ")
                .append(listId)
                .append("\n itemMainName: ")
                .append(itemMainName)
                .append("\n itemName: ")
                .append(itemName)
                .append("\n itemDescribe: ")
                .append(itemDescribe)
                .append("\n itemPrice: ")
                .append(itemPrice)
                .append("\n itemPhotoUrl: ")
                .append(itemPhotoUrl);

        logger.info(sb.toString());

        WishItemInList tmpItemInList = new WishItemInList();
        WishItem tmpItem = new WishItem();
        
        WishList list = listApi.getListById(listId);
        model.addAttribute("message", MyListBuilder.buildViewForListEdit(list));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListShare, method = RequestMethod.GET)
    public String myListsShare(
            @RequestParam("listId") long listId,
            ModelMap model) {

        WishList sharedList = listApi.getListById(listId);
        Set<WishUser> friends = userApi.getUserByLogin(getLoginCurrentLoggedUser()).getUserFriends();
        model.addAttribute("message", MyListBuilder.buildViewSharedList(friends, sharedList));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListAddShare, method = RequestMethod.POST)
    public String myListsAddShare(
            @RequestParam("listId") long listId,
            @RequestParam("friendLogin") String friendLogin,
            ModelMap model) {

        logger.info("Trying to add sharing list no " + listId + " with user: " + friendLogin);

        WishList list = listApi.getListById(listId);

        userApi.addListToUser(list, friendLogin);

        return getRedirectTo(PageUtils.MyListShare, new CustomRequestParam("listId", "" + listId));
    }

    @RequestMapping(value = PageUtils.MyListDeleteShare, method = RequestMethod.POST)
    public String myListsDeleteShare(
            @RequestParam("listId") long listId,
            @RequestParam("friendLogin") String friendLogin,
            ModelMap model) {

        logger.info("Trying to remove sharing list no " + listId + " with user: " + friendLogin);

        WishUser friend = userApi.getUserByLogin(friendLogin);
        WishList list = listApi.getListById(listId);

        if (!friend.getUserLists().contains(list)) {
            logger.warn("User already has not this list");
            return getRedirectTo(PageUtils.MyListShare, new CustomRequestParam("listId", "" + listId));
        }

        friend.getUserLists().remove(list);

        userApi.updateUser(friend);

        return getRedirectTo(PageUtils.MyListShare, new CustomRequestParam("listId", "" + listId));
    }

    @RequestMapping(value = PageUtils.MyListDelete, method = RequestMethod.GET)
    public String myListsDelete(
            @RequestParam("listId") long listId,
            ModelMap model) {

        model.addAttribute("message", "Usuwanie listy");

        listApi.deleteList(listId);

        return getRedirectTo(PageUtils.MyList);
    }

    @RequestMapping(value = PageUtils.MyListAddNewOne, method = RequestMethod.GET)
    public String myListsAddNewOne(ModelMap model) {
        if (!model.containsAttribute("message")) {
            model.addAttribute("message", "Stworzenie nowej listy");
        }

        return "lists_add_new_one";
    }

    @RequestMapping(value = PageUtils.MyListAddNewOne, method = RequestMethod.POST)
    public String myListsAddNewOne(
            @RequestParam("list_name") String listName,
            ModelMap model) {

        logger.info("Trying create new list named: " + listName);

        WishList tmpList = new WishList(listName);
        if (!listApi.createNewListForUser(tmpList, getLoginCurrentLoggedUser())) {
            model.addAttribute("message", "nie udało się dodać listy");
            return "lists_add_new_one";
        }

        return getRedirectTo(PageUtils.MyList);
    }

    @RequestMapping(value = PageUtils.login, method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = PageUtils.register, method = RequestMethod.GET)
    public String register(ModelMap model) {
        logger.info("Jestem w register");
        return "register";
    }

    @RequestMapping(value = PageUtils.register, method = RequestMethod.POST)
    public String registerSubmit(
            @RequestParam("reg_login") String login,
            @RequestParam("reg_name") String name,
            @RequestParam("reg_surname") String surname,
            @RequestParam("reg_pass") String pass,
            @RequestParam("reg_pass_conf") String passConf,
            @RequestParam("reg_email") String email,
            @RequestParam("reg_phone") String msisdn,
            ModelMap model) {
        logger.info("Jestem w register post: " + login);
        logger.info(login + " " + name + " " + surname + " " + pass + " " + passConf + " " + email + " " + msisdn);
        if (userApi.isUserExist(login)) {
            logger.info("Login: " + login + " is used");
            model.addAttribute("message", "login już jest zajęty");
            return "register";
        }

        if (!userApi.isTwoPassIdentical(pass, passConf)) {
            logger.info("Passes are diffrent: " + pass + " != " + passConf);
            model.addAttribute("message", "passwords must be the same");
            return "register";
        }

        WishUser wishUser = new WishUser(login, pass, name, surname, email, msisdn);

        userApi.createUser(wishUser);
        logger.info("Created: " + wishUser);

        model.addAttribute("message", "Utworzono konto dla użytkownika: " + login);

        return getRedirectTo(PageUtils.welcome);
    }

    @RequestMapping(value = PageUtils.MyItemBought, method = RequestMethod.GET)
    public String boughtItem(ModelMap model) {
        model.addAttribute("message", "przedmiot kupiony");
//        model.addAttribute("message", "przedmiot kupiony, remote value is: " + remoteAccess.testBySquare(new Random().nextInt(100)));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyItemBoughtCancel, method = RequestMethod.GET)
    public String cancelBoughtItem(ModelMap model) {
        model.addAttribute("message", "przedmiot został oddany");

        return "lists";
    }

    private String getLoginCurrentLoggedUser() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Long getIdCurrentLoggedUser() {
        return userApi.getUserByLogin(getLoginCurrentLoggedUser()).getId();
    }

    private String getRedirectTo(String redirectDest) {
        return "redirect:/" + redirectDest;
    }

    private String getRedirectTo(String redirectDest, CustomRequestParam... customRequestParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(getRedirectTo(redirectDest))
                .append("?");
        for (CustomRequestParam crp : customRequestParams) {
            sb.append(crp.getParamName())
                    .append("=")
                    .append(crp.getParamValue())
                    .append("&");
        }

        return sb.substring(0, sb.length() - 1);
    }

    private void setUserNameCurrentLoggedUser(ModelMap model) {
        model.addAttribute("userName", getLoginCurrentLoggedUser());
    }
}
