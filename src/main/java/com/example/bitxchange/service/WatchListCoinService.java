package com.example.bitxchange.service;

import com.example.bitxchange.model.MarketCoin;
import com.example.bitxchange.model.WatchListCoin;
import com.example.bitxchange.repository.WatchListCoinRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchListCoinService
{
   @Autowired
   WatchListCoinRepository watchListCoinRepository;

   public List<MarketCoin> getUserWatchListWithPrice (String userEmail)

    {
        List<WatchListCoin> savedCoins = watchListCoinRepository.findByUserEmail(userEmail);
        List<MarketCoin> result = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        for (WatchListCoin coin : savedCoins) {
            String symbol = coin.getSymbol();
            try {
                String url = "https://api.binance.com/api/v3/ticker/24hr?symbol=" + symbol + "USDT";
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("Binance API Response for " + symbol + ": " + response);
                JSONObject json = new JSONObject(response);

                String Symbol = symbol + "/USDT";
                String price = json.getString("lastPrice");
                String change = json.getString("priceChangePercent");
                result.add(new MarketCoin(symbol, price, change));
            } catch (Exception e) {
                System.out.println("Error fetching price for: " + symbol);
                e.printStackTrace();
            }
        }
        return result;
    }

    public void addToWatchList(String symbol, String userEmail)
    {
        watchListCoinRepository.save(new WatchListCoin(symbol, userEmail));
    }

    public void removeFromWatchList(String symbol, String userEmail)
    {
        watchListCoinRepository.deleteBySymbolAndUserEmail(symbol, userEmail);
    }
    public List<WatchListCoin> findAll(){
       return watchListCoinRepository.findAll();
    }
}
