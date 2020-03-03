package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.model.Company;
import org.bson.Document;

import java.util.ArrayList;

public class MongoInsert {
    public static void companies(MongoDatabase db, ArrayList<Company> companies) {
        MongoCollection<Document> collection = db.getCollection("companies");
        for (Company company : companies) {
            Document docCompanies = new Document();
            docCompanies.put("symbol", company.getSymbol());
            docCompanies.put("companyName", company.getCompanyName());
            docCompanies.put("description", company.getDescription());
            collection.insertOne(docCompanies);
        }

    }
}
