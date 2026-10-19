package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coins")
public class Coin
{
    @Id
    private String id;
    private String name; // BTC/USDT
    private double price; // live price
    private double change; // % change

    public Coin() {
    }

    public Coin(String id, String name, double price, double change) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.change = change;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}
