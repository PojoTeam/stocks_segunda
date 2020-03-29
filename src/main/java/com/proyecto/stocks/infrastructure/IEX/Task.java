package com.proyecto.stocks.infrastructure.IEX;

import java.util.TimerTask;

import static com.proyecto.stocks.StocksApplication.dao;

public class Task extends TimerTask {

    @Override
    public void run() {
        dao.insertCompanies(IEX.getCompanys());
    }
}
