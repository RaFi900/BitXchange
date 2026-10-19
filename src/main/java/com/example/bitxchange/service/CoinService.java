package com.example.bitxchange.service;


import com.example.bitxchange.model.Coin;
import com.example.bitxchange.repository.CoinRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CoinService
{
   private static final String BINANCE_API_URL = "https://api.binance.com/api/v3/ticker/24hr";
   private final RestTemplate restTemplate = new RestTemplate();
    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public List<Coin> fetchCoinsFromBinance()
   {
       List<Coin> coinList= new ArrayList<>();
       try
       {
           //fetch 24hr data
           String url = UriComponentsBuilder.fromHttpUrl(BINANCE_API_URL).toUriString();
           List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

           //select pairs jegulo ami dekhate chai (USDT pairs like BTCUSDT)
           for (Map<String, Object> entry : response)
           {
               String symbol = (String) entry.get("symbol");
               if (symbol.endsWith("USDT"))
               {
                   Coin coin = new Coin();
                   coin.setName(formatSymbol(symbol));
                   coin.setPrice(Double.parseDouble((String) entry.get("lastPrice")));
                   coin.setChange(Double.parseDouble((String) entry.get("priceChangePercent")));
                   coinList.add(coin);
               }
           }
           //save fresh data in MongoDB
           coinRepository.deleteAll();
           coinRepository.saveAll(coinList);

       }
       catch (Exception e) {
          System.err.println("Error fetching coins from Binance: " + e.getMessage());
       }
       return coinList;
   }

   private String formatSymbol(String rawSymbol)
   {
       //converts BTCUSDT to BTC/USDT
       return rawSymbol.replace("USDT", "/USDT");
   }


}
