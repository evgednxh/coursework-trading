package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.SoldStock;
import com.holmanskih.obsidere.model.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SoldStock> getInvestorStocks(int userID) {
        Session session = sessionFactory.getCurrentSession();
        String hqlQuery =  "from SoldStock s where s.investor.id = :userID";
        List<SoldStock> result = (ArrayList<SoldStock>) session.createQuery(hqlQuery)
                                    .setParameter("userID", userID)
                                    .list();
        return result;
    }

    @Override
    public List<SoldStock> getBusinessStocks(int userID) {
        Session session = sessionFactory.getCurrentSession();
        String hqlQuery =  "from SoldStock s where s.seller.id = :userID";
        List<SoldStock> result = (ArrayList<SoldStock>) session.createQuery(hqlQuery)
                .setParameter("userID", userID)
                .list();
        return result;
    }
}
