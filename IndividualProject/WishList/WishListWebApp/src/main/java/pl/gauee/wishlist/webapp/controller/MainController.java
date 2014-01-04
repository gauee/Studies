/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.decorators.DWishList;
import pl.gauee.wishlist.utils.decorators.DWishUser;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;
import pl.gauee.wishlist.webapp.api.ListApi;
import pl.gauee.wishlist.webapp.api.UserApi;
import pl.gauee.wishlist.webapp.factories.PersistanceAccessFactories;
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
    @Qualifier(value = "proxyBean")
    RemoteAccessApi remoteAccess;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("message", "Testuje servlet...");

        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("message", "Witaj na mojej pierwszej stronie napisanej w springu");

        return "index";
    }

    @RequestMapping(value = "/mySite", method = RequestMethod.GET)
    public String mySite(ModelMap model) {
//        User currentLoggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        logger.info("Loggedin User name is " + currentLoggedUser.getUsername());
        UserApi userApi = PersistanceAccessFactories.getInstance().getUserApi();
        DWishUser user = userApi.getDefaultUser();

        model.addAttribute("message", MySiteBuilder.build(user));

        return "mysite";
    }

    @RequestMapping(value = "/myMessages", method = RequestMethod.GET)
    public String myMessages(ModelMap model) {
        model.addAttribute("message", "Moje wiadomości");

        return "myMessages";
    }

    @RequestMapping(value = "/myFriends", method = RequestMethod.GET)
    public String myFriends(ModelMap model) {
        UserApi userApi = PersistanceAccessFactories.getInstance().getUserApi();
        DWishUser user = userApi.getDefaultUser();

        model.addAttribute("message", MyFriendBuilder.build(user));
        return "friends";
    }

    @RequestMapping(value = PageUtils.MyList, method = RequestMethod.GET)
    public String myLists(ModelMap model) {
//        User currentLoggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        logger.info("Loggedin User name is " + currentLoggedUser.getUsername());

        ListApi listApi = PersistanceAccessFactories.getInstance().getListApi();

        List<DWishList> lists = listApi.getDefaultWishList();

        model.addAttribute("message", MyListBuilder.buildViewOfAllList(lists));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListPreview, method = RequestMethod.GET)
    public String myListsPreview(ModelMap model) {

        ListApi listApi = PersistanceAccessFactories.getInstance().getListApi();

        List<DWishList> lists = listApi.getDefaultWishList();
        model.addAttribute("message", MyListBuilder.buildViewOneList(lists.get(new Random().nextInt(2))));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListEdit, method = RequestMethod.GET)
    public String myListsEdit(ModelMap model) {

        model.addAttribute("message", "Edycja");

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListShare, method = RequestMethod.GET)
    public String myListsShare(ModelMap model) {

        model.addAttribute("message", "Współdzielenie");

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyListDelete, method = RequestMethod.GET)
    public String myListsDelete(ModelMap model) {

        model.addAttribute("message", "Usuwanie listy");

        return "lists";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = PageUtils.MyItemBought, method = RequestMethod.GET)
    public String boughtItem(ModelMap model) {
//        model.addAttribute("message", "przedmiot kupiony");
        model.addAttribute("message", "przedmiot kupiony, remote value is: " + remoteAccess.testBySquare(new Random().nextInt(100)));

        return "lists";
    }

    @RequestMapping(value = PageUtils.MyItemBoughtCancel, method = RequestMethod.GET)
    public String cancelBoughtItem(ModelMap model) {
        model.addAttribute("message", "przedmiot został oddany");

        return "lists";
    }
}
