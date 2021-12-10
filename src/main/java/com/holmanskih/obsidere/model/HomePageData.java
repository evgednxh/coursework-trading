package com.holmanskih.obsidere.model;

import java.util.ArrayList;
import java.util.List;

public class HomePageData {
    private String username;
    private List<Stock> stockList;

    public HomePageData(String username, List<Stock> stockList) {
        this.username = username;
        this.stockList = new ArrayList<>();
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
