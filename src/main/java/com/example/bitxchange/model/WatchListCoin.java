package com.example.bitxchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "watchlist")
public class WatchListCoin
{
   @Id
    private String id;
    private String symbol;
    private String userEmail;

    public WatchListCoin() {
    }

    public WatchListCoin(String id, String symbol, String userEmail) {
        this.id = id;
        this.symbol = symbol;
        this.userEmail = userEmail;
    }


    public WatchListCoin(String symbol, String userEmail)
    {
         this.symbol = symbol;
         this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
