package com.proyecto.stocks.infrastructure.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class InsertMongo {
    public static void companys(MongoDatabase db) throws IOException {

        MongoCollection<Document> collection = db.getCollection("companys");

        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("axp");
        symbols.add("aapl");
        symbols.add("ba");
        symbols.add("cat");
        symbols.add("csco");
        symbols.add("cvx");
        symbols.add("xom");
        symbols.add("gs");
        symbols.add("hd");
        symbols.add("ibm");
        symbols.add("intc");
        symbols.add("jnj");
        symbols.add("ko");
        symbols.add("jpm");
        symbols.add("mcd");
        symbols.add("mmm");
        symbols.add("mrk");
        symbols.add("msft");
        symbols.add("nke");
        symbols.add("pfe");
        symbols.add("pg");
        symbols.add("trv");
        symbols.add("unh");
        symbols.add("utx");
        symbols.add("vz");
        symbols.add("v");
        symbols.add("wba");
        symbols.add("wmt");
        symbols.add("dis");
        symbols.add("dow");

        for (String symbol : symbols) {
            String urlString = "https://cloud.iexapis.com/stable/tops?token=pk_96895f0a988d4c778f2243a7f04d838e&symbols=" + symbol + "";

            URL url = new URL(urlString);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            // write the output to stdout
            String line;
            while ((line = br.readLine()) != null) {
                //sin terminar
            }

            // close our reader
            br.close();
        }

    }
}
