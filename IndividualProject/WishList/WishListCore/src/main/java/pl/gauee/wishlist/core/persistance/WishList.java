/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.util.Date;
import java.util.List;

/**
 *
 * @author gauee
 */
public class WishList {

    private int id;
    private String name;
    private Date createTime;
    private User owner;
    private List<User> coparticipants;
    private List<WishItem> items;


    public WishList(String name, User owner, List<User> coparticipants, List<WishItem> items) {
        this.id = -1;
        this.name = name;
        this.createTime = new Date();
        this.owner = owner;
        this.coparticipants = coparticipants;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User getOwner() {
        return owner;
    }

    public List<User> getCoparticipants() {
        return coparticipants;
    }

    public List<WishItem> getItems() {
        return items;
    }
    
    public int getCoparticipantsSize(){
        return coparticipants.size();
    }
    
    public boolean addNewCoparticipants(User coparticipant){
        return coparticipants.add(coparticipant);
    }
    
    public boolean removeCoparticipants(User coparticipant){
        if(coparticipants.contains(coparticipant)){
            return coparticipants.remove(coparticipant);
        }
        return false;
    }
}
