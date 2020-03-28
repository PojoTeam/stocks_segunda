package com.proyecto.stocks.controller;

import com.proyecto.stocks.model.DaoInterface;
import com.proyecto.stocks.model.DaoMongo;
import com.proyecto.stocks.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/login")
    public ResponseEntity<?> obtainAll() {
        DaoInterface dao = new DaoMongo();
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
        DaoInterface dao = new DaoMongo();
        User result = dao.getUser(userName, password);
        if(result == null){
            return  ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(result);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<User> newUser(String userName, String password) {
        if (!userName.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
            DaoInterface dao = new DaoMongo();
            dao.insertUser(userName, password);
            User saved = new User(userName, password);
            return new ResponseEntity<User>(saved, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(String userName, String password) {
        if (!userName.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
            DaoInterface dao = new DaoMongo();
            User user = dao.getUser(userName, password);

            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

}
