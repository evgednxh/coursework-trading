package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.SoldStock;

import java.util.List;

public interface SoldStockRepository {
    int add(SoldStock stock);
    List<SoldStock> getInvestorStocks(int userID);
    List<SoldStock> getBusinessStocks(int userID);
}
