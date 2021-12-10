package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.Stock;

import java.util.List;

public interface StockRepository {
    List<Stock> getAll();
    void create(Stock stock);
}
