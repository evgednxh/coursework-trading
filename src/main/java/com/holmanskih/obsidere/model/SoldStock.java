package com.holmanskih.obsidere.model;

import javax.persistence.*;

@Entity
@Table(name = "sold_stock")
public class SoldStock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "investor_id")
    private User investor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "trade_name")
    private String tradeName;

    @Column(name = "market_name")
    private String marketName;

    @Column(name = "trade_price")
    private float tradePrice;

    @Column(name = "bought_amount")
    private int boughtAmount;

    public SoldStock(int id, User investor, User seller, String tradeName, String marketName, float tradePrice, int boughtAmount) {
        this.id = id;
        this.investor = investor;
        this.seller = seller;
        this.tradeName = tradeName;
        this.marketName = marketName;
        this.tradePrice = tradePrice;
        this.boughtAmount = boughtAmount;
    }

    public SoldStock(Stock stock, User investor, User seller) {
        this.investor = investor;
        this.seller = seller;
        this.tradeName = stock.getTradeName();
        this.marketName = stock.getMarketName();
        this.tradePrice = stock.getTradePrice();
        this.boughtAmount = stock.getAmount();
    }

    public SoldStock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getInvestor() {
        return investor;
    }

    public void setInvestor(User investor) {
        this.investor = investor;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
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

    public int getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(int boughtAmount) {
        this.boughtAmount = boughtAmount;
    }
}
