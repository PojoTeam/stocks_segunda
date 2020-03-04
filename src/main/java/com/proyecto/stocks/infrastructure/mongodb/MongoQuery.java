package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.client.MongoCollection;
import com.proyecto.stocks.model.Company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoQuery {
    public static List<Company> companies() {
        MongoCollection<Company> collection = MongoConnection.getCollection();
        return collection.find().into(new ArrayList<>());
    }

    public static List<Company> symbolCompany(String symbol) {
        MongoCollection<Company> collection = MongoConnection.getCollection();
        Iterator<Company> companies = collection.find(eq("symbol",symbol)).iterator();
        List<Company> companyList = new ArrayList<>();
        companies.forEachRemaining(c -> companyList.add(c));
        return companyList;

    }
}
