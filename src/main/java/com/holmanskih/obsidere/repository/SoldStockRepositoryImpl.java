package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.SoldStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SoldStockRepositoryImpl implements SoldStockRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(SoldStock stock) {
        Session session = sessionFactory.getCurrentSession();
        int id = (Integer)session.save(stock);
        return id;
    }
}
