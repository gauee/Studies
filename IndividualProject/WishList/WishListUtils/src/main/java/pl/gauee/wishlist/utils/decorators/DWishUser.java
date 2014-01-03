/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.decorators;

import java.util.List;

/**
 *
 * @author gauee
 */
public class DWishUser {

    private String login;
    private String name;
    private String surname;
    private String email;
    private String msisdn;
    private List<DWishUser> friends;

    public DWishUser() {
    }

    public DWishUser(String login, String name, String surname, String email, String msisdn, List<DWishUser> friends) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.msisdn = msisdn;
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "DWishUser{" + "login=" + login + ", name=" + name + ", surname=" + surname + ", email=" + email + ", msisdn=" + msisdn + ", friends=" + friends + '}';
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

    public List<DWishUser> getFriends() {
        return friends;
    }

    public void setFriends(List<DWishUser> friends) {
        this.friends = friends;
    }
}
