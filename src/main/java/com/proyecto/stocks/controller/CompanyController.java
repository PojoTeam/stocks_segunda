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


@RestController
public class CompanyController {

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/invest")
    public ResponseEntity<?> obtainAll() {
        DaoInterface dao = new DaoMongo();
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
        DaoInterface dao = new DaoMongo();
        Company result = dao.getCompany(symbol);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

}
