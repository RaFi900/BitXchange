package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class TransactionHistoryForSell {
    @Id
    private String id;
    private String type;
    private String merchantName;
    private double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime date;

    public TransactionHistoryForSell() {
    }

    public TransactionHistoryForSell(String id, String type, String merchantName, double amount, String paymentMethod, String status, LocalDateTime date) {
        this.id = id;
        this.type = type;
        this.merchantName = merchantName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.date = date;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
