package com.example.bitxchange.service;

import com.example.bitxchange.model.CryptoMarket;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoMarketService
{
   private final String[] pair = {"BTCUSDT", "ETHUSDT", "XRPUSDT", "SOLUSDT", "SANDUSDT", "SUIUSDT", "TONUSDT", "ADAUSDT", "LINKUSDT", "DOGEUSDT"};

   public List<CryptoMarket> getMarketPrices()
    {
       List<CryptoMarket> prices = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (String pair : pair)
        {
            String url = "https://api.binance.com/api/v3/ticker/24hr?symbol=" + pair;
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);

            String symbol = json.getString("symbol").replace("USDT", "/USDT");
            String price = json.getString("lastPrice");
            String change = json.getString("priceChangePercent");

            prices.add(new CryptoMarket(symbol, price, change));
        }
        for (String pair : pair) {
            try {
                // API call and JSON parsing
            } catch (Exception e) {
                System.out.println("Failed to fetch " + pair + ": " + e.getMessage());
            }
        }

        return prices;
    }
}
