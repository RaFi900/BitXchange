package com.example.bitxchange.controller;


import com.example.bitxchange.model.CryptoMarket;
import com.example.bitxchange.service.CryptoMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CryptoMarketController {

    private final CryptoMarketService cryptoMarketService;

    @Autowired
    public CryptoMarketController(CryptoMarketService cryptoMarketService)
    {
      this.cryptoMarketService = cryptoMarketService;
    }

    @GetMapping("/cryptomarket")
    public String showCryptoMarket(Model model)
    {
        List<CryptoMarket> marketPrices = cryptoMarketService.getMarketPrices();
        model.addAttribute("marketData", marketPrices);
        return "cryptomarket";
    }

}
