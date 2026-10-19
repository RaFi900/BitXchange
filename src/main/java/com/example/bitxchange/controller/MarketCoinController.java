package com.example.bitxchange.controller;

import com.example.bitxchange.model.MarketCoin;
import com.example.bitxchange.service.MarketCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/market")
public class MarketCoinController
{
   private final MarketCoinService marketCoinService;

   @Autowired
    public MarketCoinController(MarketCoinService marketCoinService)
   {
       this.marketCoinService = marketCoinService;
   }

    // Show market page with all coins
    @GetMapping
    public String viewMarketPage(Model model)
    {
        List<MarketCoin> coins = marketCoinService.getAllCoins();
        model.addAttribute("marketCoins", coins);
        return "market";
    }

    // Search by symbol (used for search bar via AJAX or form)
    @GetMapping("/search")
    public String searchCoins(@RequestParam("query")  String query, Model model)
    {
        List<MarketCoin> filteredCoins = marketCoinService.searchCoins(query);
        model.addAttribute("marketCoins", filteredCoins);
        return "market";
    }

    // Optional: API endpoint to return JSON data (for real-time frontend updates if needed)
    @GetMapping("/api")
    @ResponseBody
    public List<MarketCoin> getAllCoinsJson()
    {
        return marketCoinService.getAllCoins();
    }

    // Optional: Real-time update/save from external API (Binance, etc.)
    @PostMapping("/update")
    @ResponseBody
    public String updateMarketCoin(@RequestBody MarketCoin coin)
    {
        marketCoinService.saveOrUpdate(coin);
        return "Market data updated";
    }

}
