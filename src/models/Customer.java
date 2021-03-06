/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date 20/04/18
 * @author Callum Murdoch
 */
public class Customer extends User {
    /**
     * Customer class with getters & setters and constructors
     */
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postCode;
    private HashMap <Integer, Order> orders;
    private boolean isRegistered;

    public Customer() {
        super();
        orders = new HashMap<>();
        isRegistered = true;
        
    }

    public Customer(String userName, String password, String firstName, String lastName, String addressLine1, String addressLine2, String town, String postCode) {
        super(userName, password, firstName, lastName);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postCode = postCode;
        orders = new HashMap<>();
        isRegistered = true;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getTown() {
        return town;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Integer, Order> orders) {
        this.orders = orders;
    }

    public boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
    /**
     * adds new order
     * @param newOrder
     * @throws SQLException 
     */
    public void addOrder (Order newOrder) throws SQLException
    {
        int id = newOrder.getOrderId();
        orders.put(id, newOrder);
        if(isRegistered)
        {
            DBHandler db = new DBHandler();
            int orderId = db.addOrder(newOrder, this.getUserName());
            orders.remove(id);
            orders.put(orderId, newOrder);
            orders.get(orderId).setOrderId(orderId);
        }
        
    }
    /**
     * loops though all complete orders
     * @return hashmap of all complete orders
     */
    public HashMap<Integer, Order> findAllCompleteOrders()
    {
        HashMap<Integer, Order> completeOrders = new HashMap<Integer, Order>();
        
        //loop through hashmap check the status
        for(Map.Entry<Integer, Order> entry: orders.entrySet())
        {
            if(entry.getValue().getStatus().equals("Complete"))
            {
                completeOrders.put(entry.getKey(), entry.getValue());
            }
        }
        return completeOrders;
    }
    /**
     * finds latest order
     * @return the last order
     * @throws SQLException 
     */
    public Order findLatestOrder() throws SQLException 
    {
        Order lastOrder = new Order();
        if (orders.isEmpty())
        {
            addOrder(lastOrder);
        }
        else{
            // gets customers latest orders
            lastOrder = orders.entrySet().iterator().next().getValue();
            for(Map.Entry<Integer,Order> entry: orders.entrySet())
            {
                if(entry.getValue().getOrderDate().after(lastOrder.getOrderDate()))
                {
                   lastOrder = entry.getValue();
                }
            }
            if(lastOrder.getStatus().equals("Complete"))
            {
                lastOrder = new Order();
                addOrder(lastOrder);
            }
        }
        return lastOrder;
    }
}
