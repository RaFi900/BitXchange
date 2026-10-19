package com.example.bitxchange.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "asset_snapshot")
public class AssetSnapshot
{
  @Id
    private String id;
  private double totalValue;
  private List<CoinHolding> coinHoldings;

  @CreatedDate
  private LocalDateTime date;

    public AssetSnapshot() {
    }

    public AssetSnapshot(double totalValue, List<CoinHolding> coinHoldings) {
        this.totalValue = totalValue;
        this.coinHoldings = coinHoldings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<CoinHolding> getCoinHoldings() {
        return coinHoldings;
    }

    public void setCoinHoldings(List<CoinHolding> coinHoldings) {
        this.coinHoldings = coinHoldings;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
