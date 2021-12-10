package com.holmanskih.obsidere.services;


import com.holmanskih.obsidere.model.SoldStock;
import com.holmanskih.obsidere.model.Stock;
import com.holmanskih.obsidere.model.User;
import com.holmanskih.obsidere.repository.SoldStockRepository;
import com.holmanskih.obsidere.repository.StockRepository;
import com.holmanskih.obsidere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    private StockRepository stockRepository;
    private SoldStockRepository soldStockRepository;

    @Autowired
    public void setStockRepository(StockRepository stockRepository, SoldStockRepository soldStockRepository) {
        this.stockRepository = stockRepository;
        this.soldStockRepository = soldStockRepository;
    }

    @Override
    @Transactional
    public List<Stock> getAvailableStocks() {
        List<Stock> stockList = stockRepository.getAll();
//        List<Stock> stockList = new ArrayList<>();
//        stockList.add(new Stock("ALPHB", "Alphabet", 800));
//        stockList.add(new Stock("TESLA", "Tesla", 1200));
//        stockList.add(new Stock("APPLE", "Apple", 900));
        return stockList;
    }

    @Override
    @Transactional
    public void buyStocks(SoldStock stock) {
        soldStockRepository.add(stock);
    }
}
