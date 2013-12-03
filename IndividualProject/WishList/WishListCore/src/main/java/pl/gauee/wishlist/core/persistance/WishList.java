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
    private WishUser owner;
    private List<WishUser> coparticipants;
    private List<WishItem> items;


    public WishList(String name, WishUser owner, List<WishUser> coparticipants, List<WishItem> items) {
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

    public WishUser getOwner() {
        return owner;
    }

    public List<WishUser> getCoparticipants() {
        return coparticipants;
    }

    public List<WishItem> getItems() {
        return items;
    }
    
    public int getCoparticipantsSize(){
        return coparticipants.size();
    }
    
    public boolean addNewCoparticipants(WishUser coparticipant){
        return coparticipants.add(coparticipant);
    }
    
    public boolean removeCoparticipants(WishUser coparticipant){
        if(coparticipants.contains(coparticipant)){
            return coparticipants.remove(coparticipant);
        }
        return false;
    }
}
