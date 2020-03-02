package com.proyecto.stocks.controller;

import com.proyecto.stocks.model.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    /**
     * Obtenemos todas las companys
     *
     * @return
     */

    @CrossOrigin(origins = {"*"}, allowedHeaders = "*")
    @GetMapping("/company")
    public ResponseEntity<?> obtainAll() {
        //Metodo sin terminar
        List<Company> result = null;
        return null;
    }
}
