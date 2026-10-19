package com.example.bitxchange.model;

public class CryptoMarket
{
   private String pair;
   private String price;
   private String changePercent;

    public CryptoMarket(String pair, String price, String changePercent) {
        this.pair = pair;
        this.price = price;
        this.changePercent = changePercent;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }
}
