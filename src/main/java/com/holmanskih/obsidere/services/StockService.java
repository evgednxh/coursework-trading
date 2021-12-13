package com.holmanskih.obsidere.services;

import com.holmanskih.obsidere.model.SoldStock;
import com.holmanskih.obsidere.model.Stock;
import com.holmanskih.obsidere.model.User;

import java.util.List;

public interface StockService {
    List<Stock> getAvailableStocks();
    List<SoldStock> getInvestorStocks(int userID);
    List<SoldStock> getBusinessStocks(int userID);
    void buyStocks(SoldStock stock);
    void create(Stock stock);
}
