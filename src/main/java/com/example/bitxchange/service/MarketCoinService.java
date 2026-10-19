package com.example.bitxchange.service;

import com.example.bitxchange.model.MarketCoin;
import com.example.bitxchange.repository.MarketCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketCoinService
{
   private final MarketCoinRepository marketCoinRepository;

   @Autowired
    public MarketCoinService(MarketCoinRepository marketCoinRepository)
   {
       this.marketCoinRepository = marketCoinRepository;
   }
//fetch all market coins
   public List<MarketCoin> getAllCoins()
   {
       return marketCoinRepository.findAll();
   }
//search coins by symbol
    public List<MarketCoin> searchCoins(String symbol)
    {
        if (symbol == null || symbol.isBlank())
        {
            return getAllCoins();
        }
        return marketCoinRepository.findBySymbolContainingIgnoreCase(symbol);
    }
    //save or update market coin real time updates
    public MarketCoin saveOrUpdate(MarketCoin coin)
    {
        return marketCoinRepository.save(coin);
    }
}
