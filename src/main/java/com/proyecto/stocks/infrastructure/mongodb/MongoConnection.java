package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.model.Company;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Collections;
import java.util.Objects;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {

    public static MongoCollection<Company> collection = null;

    public static void initiate() {
        char[] pass = {'r', 'e', 'n', 'a', 'i', 'd', 'o'};
        Dotenv dotenv = Dotenv.load();
        MongoCredential credential = MongoCredential.createCredential(Objects.requireNonNull(dotenv.get("MONGOUSER").toString()), "admin", Objects.requireNonNull(dotenv.get("MONGOUSER_PASS")).toCharArray());
        //MongoCredential credential = MongoCredential.createCredential("root", "admin", pass);

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .credential(credential)
                .applyToSslSettings(builder -> builder.enabled(false))
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress(dotenv.get("SERVER_IP"), 27015))))
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
