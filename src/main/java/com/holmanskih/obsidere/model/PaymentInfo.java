package com.holmanskih.obsidere.model;

import javax.persistence.*;

@Entity
@Table(name = "payment_info")
public class PaymentInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "balance")
    private int balance;

    public PaymentInfo(int userID, String cardNumber, int balance) {
        this.id = userID;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public PaymentInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int userID) {
        this.id = userID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
