package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PaymentInfoRepositoryImpl implements PaymentInfoRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int add(PaymentInfo paymentInfo) {
        Session session = sessionFactory.getCurrentSession();
        int id = (Integer)session.save(paymentInfo);
        return id;
    }

    @Override
    public void updateBalance(int id, int balance) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();

        String hqlQuery = "update PaymentInfo i set i.balance = :balance where i.id = :id";
        session.createQuery(hqlQuery)
                .setParameter("id", id)
                .setParameter("balance", balance)
                .executeUpdate();
    }
}
