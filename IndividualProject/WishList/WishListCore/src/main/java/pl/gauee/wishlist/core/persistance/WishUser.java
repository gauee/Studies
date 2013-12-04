/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gauee
 */
@Entity
@Table(name="User")
public class WishUser implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="u_id")
    private long id;
    
    @Column(name="u_login")
    private String login;
    
    @Column(name="u_pass_hash")
    private String passHash;
    
    @Column(name="u_name")
    private String name;
    
    @Column(name="u_surname")
    private String surname;

    public WishUser() {
    }

//    public WishUser(String login, String name, String surname) {
//        this.login = login;
//        this.name = name;
//        this.surname = surname;
//    }

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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", name=" + name + ", surname=" + surname + '}';
    }
    
    
}
