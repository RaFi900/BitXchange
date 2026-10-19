package com.example.bitxchange.service;

import com.example.bitxchange.model.MarketCoin;
import com.example.bitxchange.repository.MarketCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketDataFetcher
{
   private static final String BINANCE_API = "https://api.binance.com/api/v3/ticker/24hr";

   @Autowired
    private MarketCoinRepository marketCoinRepository;
   private RestTemplate restTemplate = new RestTemplate();

   @Scheduled(fixedRate = 10000) // every 10 sec
    public void fetchAndStoreMarketData()
   {
       try
       {
           BinanceTicker[] tickers = restTemplate.getForObject(BINANCE_API, BinanceTicker[].class);
           if (tickers != null)
           {
               List<MarketCoin> usdtPairs = Arrays.stream(tickers).filter(coin -> coin.getSymbol().endsWith("USDT")).map(ticker -> {
                   MarketCoin coin = new MarketCoin();
                   coin.setSymbol(ticker.getSymbol().replace("USDT", ""));
                   coin.setPrice(String.format("%.2f", Double.parseDouble(ticker.getLastPrice())));
                   coin.setChange(String.format("%.2f%%", Double.parseDouble(ticker.getPriceChangePercent())));
                   coin.setVolume(String.format("%.2fM", Double.parseDouble(ticker.getQuoteVolume()) / 1_000_000));
                   return coin;
               }).collect(Collectors.toList());

               marketCoinRepository.deleteAll();
               marketCoinRepository.saveAll(usdtPairs);
           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       }
    //inner static class match korar jonno binance api response structure
    static class BinanceTicker {
        private String symbol;
        private String lastPrice;
        private String priceChangePercent;
        private String quoteVolume;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getLastPrice() {
            return lastPrice;
        }

        public void setLastPrice(String lastPrice) {
            this.lastPrice = lastPrice;
        }

        public String getPriceChangePercent() {
            return priceChangePercent;
        }

        public void setPriceChangePercent(String priceChangePercent) {
            this.priceChangePercent = priceChangePercent;
        }

        public String getQuoteVolume() {
            return quoteVolume;
        }

        public void setQuoteVolume(String quoteVolume) {
            this.quoteVolume = quoteVolume;
        }
    }
}

