package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "p2p_transaction")
public class P2PTransaction
{
  @Id
    private String id;
  private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String userEmail;

    public P2PTransaction() {
    }

    public P2PTransaction(String id, String type, double amount, LocalDateTime timestamp, String userEmail) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
        {
         this.userEmail = userEmail;
        }
}
