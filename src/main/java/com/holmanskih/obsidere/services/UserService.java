package com.holmanskih.obsidere.services;

import com.holmanskih.obsidere.model.PaymentInfo;
import com.holmanskih.obsidere.model.User;

public interface UserService {
    String authorize(User user);
    String login(User user);
    String signOut(String token);
    User getUserData();
    User getUserByToken(String token);
    User getDataById(int userID);
    void addPaymentInfo(int userID, PaymentInfo paymentInfo);
    void updatePaymentBalance(int paymentID, int balance);
}
