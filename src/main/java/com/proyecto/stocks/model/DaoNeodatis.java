package com.proyecto.stocks.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.List;

public class DaoNeodatis implements DaoInterface {

    private ODB odb;

    public DaoNeodatis() {
        Dotenv dotenv = Dotenv.load();
        odb = ODBFactory.openClient(dotenv.get("SERVER_IP"), 8000, "bd");
    }

    @Override
    public void insertCompanies(ArrayList<Company> companies) {
        if (getAllCompanies() != null) {
            odb.delete(getAllCompanies());
        }
        for (Company company : companies) {
            odb.store(company);
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        Company company = null;
        ArrayList<Company> companies = new ArrayList<>();
        Objects<Company> objects = odb.getObjects(Company.class);

        while (objects.hasNext()) {
            company = objects.next();
            companies.add(company);
        }
        return companies;
    }

    @Override
    public Company getCompany(String symbol) {
        Company company = null;

        IQuery query = new CriteriaQuery(Company.class, Where.equal("symbol", symbol));
        Objects<Company> companies = odb.getObjects(query);

        company = companies.getFirst();
        return company;
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(Company company) {

    }

    @Override
    public List<User> getAllUsers() {
        User user = null;
        ArrayList<User> users = new ArrayList<>();
        Objects<User> objects = odb.getObjects(User.class);

        while (objects.hasNext()) {
            user = objects.next();
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUser(String userName, String password) {

        ICriterion criterion = new And().add(Where.equal("userName", userName))
                .add(Where.equal("password", password));
        CriteriaQuery query = new CriteriaQuery(User.class, criterion);
        Objects<User> objects = odb.getObjects(query);

        return objects.getFirst();
    }

    @Override
    public void insertUser(String userName, String password) {
        IQuery query = new CriteriaQuery(Company.class, Where.equal("userName", userName));
        Objects<User> objects = odb.getObjects(query);

        if (userName.equalsIgnoreCase(objects.getFirst().getUserName())) {

        } else {
            ArrayList<PurchasedCompany> purchasedCompanies = new ArrayList<>();
            User user = new User(userName, password, purchasedCompanies);
            odb.store(user);
        }
    }

    @Override
    public User addBuy(String userName, PurchasedCompany purchasedCompany) {
        IQuery query = new CriteriaQuery(Company.class, Where.equal("userName", userName));
        Objects<User> objects = odb.getObjects(query);
        User user = objects.getFirst();
        ArrayList<PurchasedCompany> companies = user.getCompanies();

        companies.add(purchasedCompany);
        user.setCompanies(companies);

        odb.store(user);

        return user;
    }
}
