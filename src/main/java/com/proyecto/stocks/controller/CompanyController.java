package com.proyecto.stocks.controller;

import com.proyecto.stocks.infrastructure.mongodb.MongoConnection;
import com.proyecto.stocks.infrastructure.mongodb.MongoQuery;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CompanyController {

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/company")
    public ResponseEntity<?> obtainAll() {
        List<Document> result = MongoQuery.companies(MongoConnection.initiate());
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
