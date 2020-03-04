package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.model.Company;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoQuery {
    public static List<Company> companies() {
        MongoCollection<Company> collection = MongoConnection.getCollection();
        return collection.find().into(new ArrayList<>());
    }
}
