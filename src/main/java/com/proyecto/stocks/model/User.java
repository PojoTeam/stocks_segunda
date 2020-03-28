package com.proyecto.stocks.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName;
    private String password;
    private ArrayList<PurchasedCompany> companies;

    public User() {
    }

    public User(String userName, String password, ArrayList<PurchasedCompany> companies) {
        this.userName = userName;
        this.password = password;
        this.companies = companies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<PurchasedCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<PurchasedCompany> companies) {
        this.companies = companies;
    }
}
