package com.example.bitxchange.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coin_holding")
public class CoinHolding
{
  private String name;
  private double quantity;
  private double usdtValue;

  public CoinHolding() {
    }

    public CoinHolding(String name, double quantity, double usdtValue) {
        this.name = name;
        this.quantity = quantity;
        this.usdtValue = usdtValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUsdtValue() {
        return usdtValue;
    }

    public void setUsdtValue(double usdtValue) {
        this.usdtValue = usdtValue;
    }
}
