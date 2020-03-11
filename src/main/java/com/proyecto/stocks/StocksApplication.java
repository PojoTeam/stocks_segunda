package com.proyecto.stocks;

import com.proyecto.stocks.infrastructure.IEX.IEX;
import com.proyecto.stocks.infrastructure.mongodb.MongoInsert;
import com.proyecto.stocks.infrastructure.neodatis.NeodatisConnection;
import com.proyecto.stocks.infrastructure.neodatis.NeodatisInsert;
import com.proyecto.stocks.model.Company;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.ArrayList;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class StocksApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        //ArrayList<Company> companies = IEX.getCompanys();
        //MongoInsert.companies(companies);
        //NeodatisInsert.companies(companies, NeodatisConnection.innit());
    }
}
