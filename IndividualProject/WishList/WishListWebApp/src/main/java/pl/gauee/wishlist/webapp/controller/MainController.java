/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.gauee.wishlist.utils.decorators.DWishUser;
import pl.gauee.wishlist.webapp.api.UserApi;
import pl.gauee.wishlist.webapp.factories.PersistanceAccessFactories;
import pl.gauee.wishlist.webapp.html.MyFriendBuilder;
import pl.gauee.wishlist.webapp.html.MySiteBuilder;

/**
 *
 * @author gauee
 */
@Controller
//@RequestMapping("/")
public class MainController {

    private final Logger logger = Logger.getLogger(MainController.class);

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
        User currentLoggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Loggedin User name is " + currentLoggedUser.getUsername());
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

    @RequestMapping(value = "/myLists", method = RequestMethod.GET)
    public String myLists(ModelMap model) {
        model.addAttribute("message", "Moje listy");

        return "lists";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }
}
