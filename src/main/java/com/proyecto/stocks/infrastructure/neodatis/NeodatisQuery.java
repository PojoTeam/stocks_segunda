package com.proyecto.stocks.infrastructure.neodatis;

import com.proyecto.stocks.model.Company;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

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

    public static Company company(ODB odb, String symbol) {
        Company company = null;

        IQuery query = new CriteriaQuery(Company.class, Where.equal("symbol", symbol));
        Objects<Company> companies = odb.getObjects(query);

        company = companies.getFirst();
        return company;
    }
}
