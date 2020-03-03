package com.proyecto.stocks.infrastructure.IEX;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.stocks.model.Company;

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

        String[] symbols = {"axp", "aapl", "ba", "cat", "csco", "cvx", "xom", "gs", "hd", "ibm", "intc", "jnj", "ko", "jmp", "mcd", "mmm", "mrk",
                "msft", "nke", "pfe", "pg", "trv", "unh", "utx", "vz", "v", "wba", "wmt", "dis", "dow"};

        ObjectMapper mapper = new ObjectMapper();

        for (String symbol : symbols) {
            String urlString = "https://sandbox.iexapis.com/stable/stock/" + symbol + "/company?token=Tsk_f17894b062044e64be9a0403cca7166d";
            try {
                URL url = new URL(urlString);

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

                String json = br.readLine();

                company = mapper.readValue(json, Company.class);

                companies.add(company);

                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return companies;
    }
}
