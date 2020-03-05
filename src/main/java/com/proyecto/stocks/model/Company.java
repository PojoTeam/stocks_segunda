package com.proyecto.stocks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable {
    private String companyName;
    private String symbol;
    private String description;
    private String logo;
    private long marketCap;

    public Company() {
    }

    public Company(String companyName, String symbol, String description, String logo, long marketCap) {
        this.companyName = companyName;
        this.symbol = symbol;
        this.description = description;
        this.logo = logo;
        this.marketCap = marketCap;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }
}
