package com.holmanskih.obsidere.services;

import com.holmanskih.obsidere.model.SoldStock;
import com.holmanskih.obsidere.model.Stock;
import com.holmanskih.obsidere.model.User;

import java.util.List;

public interface StockService {
    List<Stock> getAvailableStocks();
    void buyStocks(SoldStock stock);
}
