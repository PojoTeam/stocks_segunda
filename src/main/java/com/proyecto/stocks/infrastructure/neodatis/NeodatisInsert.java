package com.proyecto.stocks.infrastructure.neodatis;

import com.proyecto.stocks.model.Company;
import org.neodatis.odb.ODB;

import java.util.ArrayList;

public class NeodatisInsert {
    public static void companies(ArrayList<Company> companies, ODB odb) {
        for (Company company: companies){
            odb.store(company);
        }
    }
}
