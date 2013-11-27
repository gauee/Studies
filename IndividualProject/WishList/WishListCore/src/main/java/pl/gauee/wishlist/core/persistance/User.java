/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.io.Serializable;
import org.hibernate.annotations.Entity;

/**
 *
 * @author gauee
 */
@Entity
public class User implements Serializable{
    
    private int id;
    private String login;
    private String firstName;
    private String secondName;
    
}
