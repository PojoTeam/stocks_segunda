package com.proyecto.stocks.infrastructure.neodatis;

import com.proyecto.stocks.model.Company;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;

import java.util.ArrayList;

public class NeodatisQuery {
    public static ArrayList<Company> companies(ODB odb) {
        Company company = null;
        ArrayList<Company> companies = null;
        Objects<Company> objects = odb.getObjects(Company.class);

        while (objects.hasNext()) {
            company = objects.next();
            companies.add(company);
        }
        return companies;
    }
}
