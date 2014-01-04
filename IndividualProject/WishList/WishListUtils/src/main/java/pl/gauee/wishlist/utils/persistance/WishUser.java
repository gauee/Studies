/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author gauee
 */
@Entity
@Table(name = "WishUser")
public class WishUser implements WishObject {

    private static final long serialVersionUID = -4388501513356374532L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wu_id")
    private long id;
    @Column(name = "wu_login")
    private String login;
    @Column(name = "wu_pass_hash")
    private String passHash;
    @Column(name = "wu_name")
    private String name;
    @Column(name = "wu_surname")
    private String surname;
    @Column(name = "wu_email")
    private String email;
    @Column(name = "wu_msisdn")
    private String msisdn;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "Wish_User_List", joinColumns = {
        @JoinColumn(name = "wul_wu_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "wul_wl_id")
    })
    private Set<WishList> userLists = new HashSet<WishList>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "Wish_User_User",
            joinColumns = {
        @JoinColumn(name = "wuu_wu_id1")
    },
            inverseJoinColumns = {
        @JoinColumn(name = "wuu_wu_id2")
    })
    private Set<WishUser> userFriends = new HashSet<WishUser>();

    public WishUser() {
    }

    public WishUser(String login, String passHash, String name, String surname, String email, String msisdn) {
        this.id = id;
        this.login = login;
        this.passHash = passHash;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        return "WishUser{" + "id=" + id + ", login=" + login + ", passHash=" + passHash + ", name=" + name + ", surname=" + surname + ", email=" + email + ", msisdn=" + msisdn + ", userLists=" + userLists + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
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

    public List<WishUser> getFriends() {
        return null;
    }

    public Set<WishList> getUserLists() {
        return userLists;
    }

    public void setUserLists(Set<WishList> userLists) {
        this.userLists = userLists;
    }
}
