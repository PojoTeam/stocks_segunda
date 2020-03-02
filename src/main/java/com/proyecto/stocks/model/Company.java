package com.proyecto.stocks.model;

import java.io.Serializable;

public class Company implements Serializable {
    private String symbol;
    private String name;
    private String description;
    private float marketCap;

    public Company() {
    }

    public Company(String symbol, String name, String description, float marketCap) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.marketCap = marketCap;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(float marketCap) {
        this.marketCap = marketCap;
    }
}
