package com.proyecto.stocks.controller;

import com.proyecto.stocks.model.Company;
import com.proyecto.stocks.model.DaoInterface;
import com.proyecto.stocks.model.DaoMongo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.proyecto.stocks.StocksApplication.dao;


@RestController
public class CompanyController {

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/invest")
    public ResponseEntity<?> obtainAll() {
        List<Company> result = dao.getAllCompanies();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/invest/{symbol}")
    public ResponseEntity<?> obtainOne(@PathVariable String symbol) {
        Company result = dao.getCompany(symbol);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

}
