package com.proyecto.stocks.model;

import java.io.Serializable;

public class PurchasedCompany implements Serializable{
    private String symbol;
    private int quantity;
    private float price;


    public PurchasedCompany() {
    }

    public PurchasedCompany(String symbol, int quantity, float price) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
