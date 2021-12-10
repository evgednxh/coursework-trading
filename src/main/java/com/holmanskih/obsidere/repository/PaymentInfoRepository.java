package com.holmanskih.obsidere.repository;

import com.holmanskih.obsidere.model.PaymentInfo;

public interface PaymentInfoRepository {
    int add(PaymentInfo paymentInfo);
    void updateBalance(int id, int balance);
}
