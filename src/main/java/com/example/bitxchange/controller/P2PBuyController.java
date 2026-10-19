package com.example.bitxchange.controller;

import com.example.bitxchange.model.Merchant;
import com.example.bitxchange.model.TransactionHistory;
import com.example.bitxchange.service.MerchantService;
import com.example.bitxchange.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/p2pbuy")
public class P2PBuyController
{
   private final MerchantService merchantService;
   private final TransactionHistoryService transactionHistoryService;


    public P2PBuyController(MerchantService merchantService, TransactionHistoryService transactionHistoryService)
   {
       this.merchantService = merchantService;
       this.transactionHistoryService = transactionHistoryService;
   }

   @GetMapping
    public String showP2PBuyPage(Model model)
   {
       List<Merchant> merchant = merchantService.getAllMerchants();
       List<TransactionHistory> transaction = transactionHistoryService.getAllBuyTransactions();

       model.addAttribute("merchantList", merchant);
       model.addAttribute("transactionList", transaction);
       return "p2pbuy";
   }

   @PostMapping("/buy")
    public String handleByRequest(@RequestParam("merchantId") String merchantId, @RequestParam("amount") double amount)
   {
       Merchant merchant = merchantService.getMerchantById(merchantId).orElse(null);
       if (merchant == null)
       {
           return "redirect:/p2pbuy/buy?error=merchantNotFound";
       }

       TransactionHistory transactionHistory = new TransactionHistory();
       transactionHistory.setType("Buy");
       transactionHistory.setMerchantName(merchant.getName());
       transactionHistory.setAmount(amount);
       transactionHistory.setPaymentMethod(merchant.getPaymentMethod());
       transactionHistory.setStatus("Completed");
       transactionHistory.setDate(LocalDateTime.now());
       transactionHistoryService.saveTransaction(transactionHistory);

       return "redirect:/p2pbuy/buy?success=true";
   }

    @GetMapping("/buy")
    public String redirectToBuy(Model model,
                                @RequestParam(value = "success", required = false) String success,
                                @RequestParam(value = "error", required = false) String error) {
        List<Merchant> merchants = merchantService.getAllMerchants();
        List<TransactionHistory> transactions = transactionHistoryService.getAllBuyTransactions();

        model.addAttribute("merchantList", merchants);
        model.addAttribute("transactionList", transactions);
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        return "p2pbuy";
    }

}
