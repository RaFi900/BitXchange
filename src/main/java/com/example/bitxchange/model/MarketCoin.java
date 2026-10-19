package com.example.bitxchange.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market_coins")
public class MarketCoin {

    @Id
    private String id;

    private String symbol;
    private String price;
    private String change;
    private String volume;

    public MarketCoin() {
    }

    public MarketCoin(String symbol, String price, String change, String volume) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.volume = volume;
    }

    public MarketCoin(String symbol, String price, String change) {
        this.symbol = symbol;
        this.price = price;
        this.change = change;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}

