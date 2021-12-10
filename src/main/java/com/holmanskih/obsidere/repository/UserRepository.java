package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;

public interface UserRepository {
    User getByID(int id);
    User getByToken(String token);
    void updateUserToken(String token);
    void updateUserPaymentInfo(int userID, PaymentInfo paymentInfo);
//    void updateBalance(int userID, int balance);
    void add(User user);
}
