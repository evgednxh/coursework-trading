package com.holmanskih.obsidere.model;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private User seller;

    @Column(name = "trade_name")
    private String tradeName;

    @Column(name = "market_name")
    private String marketName;

    @Column(name = "trade_price")
    private float tradePrice;

    @Column(name = "amount")
    private int amount;

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Stock() {}

    public Stock(String tradeName, String marketName, float tradePrice) {
        this.tradeName = tradeName;
        this.marketName = marketName;
        this.tradePrice = tradePrice;
    }

    public Stock(String tradeName, String marketName, float tradePrice, int amount) {
        this.tradeName = tradeName;
        this.marketName = marketName;
        this.tradePrice = tradePrice;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public float getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(float tradePrice) {
        this.tradePrice = tradePrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", seller=" + seller +
                ", tradeName='" + tradeName + '\'' +
                ", marketName='" + marketName + '\'' +
                ", tradePrice=" + tradePrice +
                ", amount=" + amount +
                '}';
    }
}
