package com.proyecto.stocks;

import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.infrastructure.mongodb.ConnectionMongo;
import com.proyecto.stocks.infrastructure.mongodb.InsertMongo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocksApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MongoDatabase db = ConnectionMongo.initiate();
        InsertMongo.companys(db);
    }
}
