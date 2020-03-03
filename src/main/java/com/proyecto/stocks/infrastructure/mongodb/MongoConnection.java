package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    public static MongoDatabase initiate() {
        MongoClient client = new MongoClient("localhost", 27017);
        return client.getDatabase("stocksdb");
    }
}
