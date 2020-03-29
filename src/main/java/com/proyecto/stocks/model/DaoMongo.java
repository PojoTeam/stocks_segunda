package com.proyecto.stocks.model;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.infrastructure.mongodb.MongoConnection;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DaoMongo implements DaoInterface {

    private MongoDatabase database;

    public DaoMongo() {
        Dotenv dotenv = Dotenv.load();
        MongoCredential credential = MongoCredential.createCredential(Objects.requireNonNull(dotenv.get("MONGOUSER")), "admin", Objects.requireNonNull(dotenv.get("MONGOUSER_PASS")).toCharArray());

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
        database = mongoClient.getDatabase("stocksdb");
    }

    @Override
    public void insertCompanies(ArrayList<Company> companies) {
        MongoCollection<Company> collection = MongoConnection.getCollection();
        collection.insertMany(companies);
    }

    @Override
    public List<Company> getAllCompanies() {
        MongoCollection<Company> collection = database.getCollection("companies", Company.class);
        return collection.find().into(new ArrayList<>());
    }

    @Override
    public Company getCompany(String symbol) {
        MongoCollection<Company> collection = database.getCollection("companies", Company.class);
        Company company = collection.find(eq("symbol",symbol)).first();
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
        return null;
    }

    @Override
    public User getUser(String userName, String password) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        User user = collection.find(and(eq("userName", userName), eq("password", password))).first();

        return user;
    }

    @Override
    public void insertUser(String userName, String password) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        ArrayList<PurchasedCompany> purchasedCompanies = new ArrayList<>();
        User user = new User(userName, password, purchasedCompanies);
        collection.insertOne(user);
    }

    @Override
    public User addBuy(String userName, PurchasedCompany purchasedCompany) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        User user = collection.find(eq("userName", userName)).first();

        ArrayList<PurchasedCompany> companies = user.getCompanies();
        companies.add(purchasedCompany);
        user.setCompanies(companies);

        collection.deleteOne(eq("userName", userName));

        collection.insertOne(user);

        return user;
    }
}
