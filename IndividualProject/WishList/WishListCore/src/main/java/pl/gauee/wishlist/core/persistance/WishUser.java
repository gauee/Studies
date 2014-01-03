/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pl.gauee.wishlist.core.api.WishObject;

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
    private long msisdn;

    public WishUser() {
    }

    @Override
    public String toString() {
        return "WishUser{" + "id=" + id + ", login=" + login + ", passHash=" + passHash + ", name=" + name + ", surname=" + surname + ", email=" + email + ", msisdn=" + msisdn + '}';
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

    public long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(long msisdn) {
        this.msisdn = msisdn;
    }
}
