package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "merchantn")
public class Merchant
{
   @Id
    private String id;
   private String name;
   private String paymentMethod;
   private String userAccount;
   private double price;
   private double available;

    public Merchant() {
    }

    public Merchant(String id, String name, String paymentMethod, String userAccount, double price, double available) {
        this.id = id;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.userAccount = userAccount;
        this.price = price;
        this.available = available;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }
}
