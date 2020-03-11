package com.proyecto.stocks.model;

import org.bson.types.ObjectId;

import java.util.List;

public interface DaoInterface {
    public List<Company> getAllCompanies();
    public Company getCompany(String symbol);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);

    public List<User> getAllUsers();
    public User getUserId(String userName, String password);
    public void insertUser(String userName, String password);
}