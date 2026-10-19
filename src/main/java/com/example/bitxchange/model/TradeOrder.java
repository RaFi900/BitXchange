package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "trade_orders")
public class TradeOrder
{
    @Id
    private String id;
    private String type; // buy or sell
    private String coinName;
    private double quantity;
    private double totalAmount;
    private double breakEvenPrice; // only buy
    private LocalDateTime timestamp;
    private String symbol;
    private String userEmail;

    public TradeOrder() {
    }

    public TradeOrder(String id, String type, String coinName, double quantity, double totalAmount, double breakevenPrice, LocalDateTime timestamp, String symbol, String userEmail) {
        this.id = id;
        this.type = type;
        this.coinName = coinName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.breakEvenPrice = breakevenPrice;
        this.timestamp = timestamp;
        this.symbol = symbol;
        this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getBreakevenPrice() {
        return breakEvenPrice;
    }

    public void setBreakevenPrice(double breakevenPrice) {
        this.breakEvenPrice = breakevenPrice;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
