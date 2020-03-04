package com.proyecto.stocks.infrastructure.IEX;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.stocks.model.Company;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class IEX {
    /**
     * Método para recoger datos de la API IEX y pasar el resultado JSON a un objeto.
     *
     * @return ArrayList de Company
     */
    public static ArrayList<Company> getCompanys() {
        ArrayList<Company> companies = new ArrayList<Company>();
        Company company = null;

        String[] symbols = {"AXP","AAPL","BA","CAT","CSCO","CVX","XOM","GS","HD","IBM",
                "INTC","JNJ","KO","JPM","MCD","MMM","MRK","MSFT","NKE","PFE","PG",
                "TRV","UNH","UTX","VZ","V","WBA","WMT","DIS","DOW"};

        ObjectMapper mapper = new ObjectMapper();
        Dotenv dotenv = Dotenv.load();

        for (String symbol : symbols) {
            String urlString = "https://cloud.iexapis.com/stable/stock/" + symbol + "/company?token=" + dotenv.get("API_KEY");
            String urlStringLogo = "https://storage.googleapis.com/iexcloud-hl37opg/api/logos/" + symbol + ".png";
            try {
                URL url = new URL(urlString);

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String json = br.readLine();

                company = mapper.readValue(json, Company.class);
                company.setLogo(urlStringLogo);

                companies.add(company);

                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return companies;
    }
}
