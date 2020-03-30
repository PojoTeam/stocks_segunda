package com.proyecto.stocks.model;

import java.util.ArrayList;
import java.util.List;

public interface DaoInterface {

    public void insertCompanies(ArrayList<Company> companies);

    public List<Company> getAllCompanies();

    public Company getCompany(String symbol);

    public List<User> getAllUsers();

    public User getUser(String userName, String password);

    public void insertUser(String userName, String password);

    public User addBuy(String userName, PurchasedCompany purchasedCompany);

}