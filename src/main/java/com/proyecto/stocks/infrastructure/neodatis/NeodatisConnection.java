package com.proyecto.stocks.infrastructure.neodatis;

import io.github.cdimascio.dotenv.Dotenv;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class NeodatisConnection {

    public static ODB innit () {
        Dotenv dotenv = Dotenv.load();
        ODB odb = ODBFactory.openClient(dotenv.get("SERVER_IP"),8000,"bd");
        return odb;
    }
}
