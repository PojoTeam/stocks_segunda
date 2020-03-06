package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.client.MongoCollection;
import com.proyecto.stocks.model.Company;

import java.util.ArrayList;

public class MongoInsert {
    public static void companies(ArrayList<Company> companies) {
        MongoCollection<Company> collection = MongoConnection.getCollection();
        //collection.insertMany(companies);
    }
}
