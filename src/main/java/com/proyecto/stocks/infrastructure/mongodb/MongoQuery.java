package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoQuery {
    public static List<Document> companies(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("companies");
        return collection.find().into(new ArrayList<>());
    }
}
