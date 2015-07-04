/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.sql.Date;
/**
 *
 * @author Tal
 */
public class ItemData {
    
    private String name ;
    private String itemIDWebSite ;
    private double itemPrice ;
    private int quantity ;
    private double totalPrice ;
    private String imageURL ;
    private double shippingCost ;
    private String ETA ;
    private boolean arrived;

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemIDWebSite(String itemIDWebSite) {
        this.itemIDWebSite = itemIDWebSite;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }
        
    
}
