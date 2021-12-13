package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.Stock;
import com.holmanskih.obsidere.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepositoryImpl implements StockRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Stock> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Stock> result =  session.createQuery("from Stock").list();
        return result;
    }

    @Override
    public void create(Stock stock) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(stock);
        tx.commit();
        session.close();
    }
}
