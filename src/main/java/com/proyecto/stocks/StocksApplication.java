package com.proyecto.stocks;

import com.mongodb.client.MongoDatabase;
import com.proyecto.stocks.infrastructure.IEX.IEX;
import com.proyecto.stocks.infrastructure.mongodb.MongoConnection;
import com.proyecto.stocks.infrastructure.mongodb.MongoInsert;
import com.proyecto.stocks.model.Company;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class StocksApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        ArrayList<Company> companies = IEX.getCompanys();
        MongoInsert.companies(companies);
    }
}
