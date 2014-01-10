/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance.rest;

import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class RestWishUser extends RestObject<RestWishUser, WishUser> {

    private static final long serialVersionUID = -7310580863244092166L;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String msisdn;

    public RestWishUser() {
    }

    @Override
    public String toString() {
        return "RestWishUser{" + "id=" + id + ", login=" + login + ", name=" + name + ", surname=" + surname + ", email=" + email + ", msisdn=" + msisdn + '}';
    }

    @Override
    public RestWishUser packTo(WishUser sourceObject) {
        RestWishUser resultObject = new RestWishUser();
        if (null == sourceObject) {
            return resultObject;
        }
        resultObject.setId(sourceObject.getId());
        resultObject.setLogin(sourceObject.getLogin());
        resultObject.setName(sourceObject.getName());
        resultObject.setSurname(sourceObject.getSurname());
        resultObject.setEmail(sourceObject.getEmail());
        resultObject.setMsisdn(sourceObject.getMsisdn());

        return resultObject;
    }

    @Override
    public WishUser repackFrom(RestWishUser sourceObject) {
        WishUser resultObject = new WishUser();
        if (null == sourceObject) {
            return resultObject;
        }
        resultObject.setId(sourceObject.getId());
        resultObject.setLogin(sourceObject.getLogin());
        resultObject.setName(sourceObject.getName());
        resultObject.setSurname(sourceObject.getSurname());
        resultObject.setEmail(sourceObject.getEmail());
        resultObject.setMsisdn(sourceObject.getMsisdn());

        return resultObject;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
