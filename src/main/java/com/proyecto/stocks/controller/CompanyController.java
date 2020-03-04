package com.proyecto.stocks.controller;

import com.google.gson.Gson;
import com.proyecto.stocks.infrastructure.mongodb.MongoQuery;
import com.proyecto.stocks.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CompanyController {

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/invest")
    public ResponseEntity<?> obtainAll() {
        List<Company> result = MongoQuery.companies();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/invest/{symbol}")
    public ResponseEntity<?> obtainOne(@PathVariable String symbol) {
        List<Company> result = MongoQuery.symbolCompany(symbol);
        if(result.isEmpty()){
            return  ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(result);
        }
    }
}
