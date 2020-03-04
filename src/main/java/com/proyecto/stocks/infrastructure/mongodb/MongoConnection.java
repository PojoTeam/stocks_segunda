package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.model.Company;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Collections;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {

    public static MongoCollection<Company> collection = null;

    public static void initiate() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .applyToSslSettings(builder -> builder.enabled(false))
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017))))
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("stocksdb");
        collection = database.getCollection("companies", Company.class);
    }

    public static MongoCollection<Company> getCollection() {
        if(collection == null) initiate();
        return collection;
    }
}
