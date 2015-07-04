/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Logic;

import java.sql.Date;
import java.util.LinkedList;
/**
 *
 * @author Tal
 */
public class OrderData {
    
    private String userName ;
    private String shoppingWebSite ;
    private String orderID ;
    private String dateOrderReceivedToParse;
    private String orderDate ;
    private String address ;
    private double shippingCost ; 
    private double totalPrice ;
    private String currency ;
    private boolean arrived;

    private LinkedList<ItemData> items;

    public OrderData()
    {
        items = new LinkedList<ItemData>(); 
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public void setShoppingWebSite(String shoppingWebSite) {
        this.shoppingWebSite = shoppingWebSite;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setDateOrderReceivedToParse(String dateOrderReceivedToParse) {
        this.dateOrderReceivedToParse = dateOrderReceivedToParse;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setItems(LinkedList<ItemData> items) {
        this.items = items;
    }

    public LinkedList<ItemData> getItems() {
        return items;
    }
    
    

    
}
