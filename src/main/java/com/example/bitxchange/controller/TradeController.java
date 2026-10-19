package com.example.bitxchange.controller;

import com.example.bitxchange.model.Coin;
import com.example.bitxchange.model.TradeOrder;
import com.example.bitxchange.repository.CoinRepository;
import com.example.bitxchange.service.CoinService;
import com.example.bitxchange.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;



@Controller
@RequestMapping("/trade")
public class TradeController
{
   private final CoinService coinService;
   private final TradeOrderService tradeOrderService;
    private final CoinRepository coinRepository;

    @Autowired
   public TradeController(CoinService coinService, TradeOrderService tradeOrderService, CoinRepository coinRepository)
   {
       this.coinService = coinService;
       this.tradeOrderService = tradeOrderService;
       this.coinRepository = coinRepository;
   }

    // Show trade page
    @GetMapping
    public String showTradePage(Model model)
    {
        List<Coin> coins = coinService.fetchCoinsFromBinance();
        List<TradeOrder> orders = tradeOrderService.getAllOrders();

        model.addAttribute("coins", coins);
        model.addAttribute("orders", orders);
        return "trade";
    }

    // Handle buy action
    @PostMapping("/buy")
    public String handleBuy(@RequestParam("coinName") String coinName,
                            @RequestParam("quantity") double quantity,
                            @RequestParam("price") double price)
    {
        double total = quantity * price;
        TradeOrder order = new TradeOrder();
                order.setType("Buy");
                order.setCoinName(coinName);
                order.setQuantity(quantity);
                order.setTotalAmount(total);
                order.setBreakevenPrice(0);
                order.setTimestamp(LocalDateTime.now());

                tradeOrderService.saveOrder(order);
                return "redirect:/trade";
    }

    // REST api get live price of a coin
    @GetMapping("/price")
    @ResponseBody
    public Double getLivePrice(@RequestParam("symbol") String symbol)
    {
        List<Coin> coins = coinService.fetchCoinsFromBinance();
        return coins.stream()
                .filter(coin -> coin.getName().equalsIgnoreCase(symbol))
                .findFirst()
                .map(Coin::getPrice)
                .orElse(null);
    }


}
