/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Date 20/04/18
 * @author Callum Murdoch
 */
public class Product {
    /**
     * Product class with getters & setters and constructors
     */
    private int productId;
    private String productName;
    private double price;
    private int StockLevel;

    public Product() {
    }

    public Product(int productId, String productName, double price, int StockLevel) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.StockLevel = StockLevel;
    }
    public Product( String productName, double price, int StockLevel) {
        
        this.productName = productName;
        this.price = price;
        this.StockLevel = StockLevel;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStockLevel() {
        return StockLevel;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockLevel(int StockLevel) {
        this.StockLevel = StockLevel;
    }

    @Override
    public String toString() {
        return  productName ;
    }
    
    
    
}
