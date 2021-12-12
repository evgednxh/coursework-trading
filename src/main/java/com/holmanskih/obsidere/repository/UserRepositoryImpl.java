package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getByToken(String token) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User u where u.accessToken = :token");
        query.setParameter("token", token);
        List<User> result = (ArrayList<User>) query.list();
        if(result.size() == 0) {
            return null;
        }
        User user = (User) result.get(0);
        return user;
    }

    @Override
    public void updateUserTokenByEmail(String email, String token) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hqlQuery = "update User u set u.accessToken = :token where u.email = :email";
        session.createQuery(hqlQuery)
                .setParameter("email", email)
                .setParameter("token", token)
                .executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public void updateUserToken(String token) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        String hqlQuery = "update User u set u.accessToken = :oldAccessToken where u.accessToken = :newAccessToken";
        session.createQuery(hqlQuery)
                .setParameter("newAccessToken", "")
                .setParameter("oldAccessToken", token)
                .executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public void updateUserPaymentInfo(int userID, PaymentInfo paymentInfo) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();

        String hqlQuery = "update User u set u.payment = :paymentInfo where u.id = :userID";
        session.createQuery(hqlQuery)
                .setParameter("userID", userID)
                .setParameter("paymentInfo", paymentInfo)
                .executeUpdate();
//        tx.commit();
//        session.close();
    }

    @Override
    public User getByEmail(String email, String username) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);
        List<User> result = (ArrayList<User>) query.list();
        if(result.size() == 0) {
            return null;
        }
        User user = (User) result.get(0);
        return user;
    }

//    @Override
//    public void updateBalance(int paymentID, int balance) {
//        Session session = sessionFactory.getCurrentSession();
////        Transaction tx = session.beginTransaction();
//
//        String hqlQuery = "update User u set u.payment.balance = :balance where u.payment.id = :paymentID";
//        session.createQuery(hqlQuery)
//                .setParameter("paymentID", paymentID)
//                .setParameter("balance", balance)
//                .executeUpdate();
////        tx.commit();
////        session.close();
//    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }
}
