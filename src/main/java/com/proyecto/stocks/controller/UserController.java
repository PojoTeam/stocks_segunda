package com.proyecto.stocks.controller;

import com.proyecto.stocks.model.DaoInterface;
import com.proyecto.stocks.model.DaoMongo;
import com.proyecto.stocks.model.PurchasedCompany;
import com.proyecto.stocks.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.proyecto.stocks.StocksApplication.dao;

@RestController
public class UserController {

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/login")
    public ResponseEntity<?> obtainAll() {
        List<User> result = dao.getAllUsers();
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/login/{userName}")
    public ResponseEntity<?> obtainOne(@PathVariable String userName, String password) {
        User result = dao.getUser(userName, password);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<User> newUser(String userName, String password) {
        if (!userName.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
            dao.insertUser(userName, password);
            ArrayList<PurchasedCompany> purchasedCompanies = new ArrayList<>();
            User saved = new User(userName, password, purchasedCompanies);
            return new ResponseEntity<User>(saved, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(String userName, String password) {
        if (!userName.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
            User user = dao.getUser(userName, password);

            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @PostMapping("/buy")
    public ResponseEntity<User> buy(String userName, String symbol, int quantity, float price) {
        if (!userName.equalsIgnoreCase("") && !symbol.equalsIgnoreCase("")) {

            PurchasedCompany purchasedCompany = new PurchasedCompany(symbol, quantity, price);

            User saved = dao.addBuy(userName, purchasedCompany);

            return new ResponseEntity<User>(saved, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
}
