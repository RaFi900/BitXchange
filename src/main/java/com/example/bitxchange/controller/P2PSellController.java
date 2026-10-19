package com.example.bitxchange.controller;

import com.example.bitxchange.model.Merchant;
import com.example.bitxchange.model.TransactionHistory;
import com.example.bitxchange.model.TransactionHistoryForSell;
import com.example.bitxchange.service.MerchantService;
import com.example.bitxchange.service.TransactionHistoryService;
import com.example.bitxchange.service.TransactionHistoryServiceForSell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/p2psell")
public class P2PSellController {
    private final MerchantService merchantService;
    private final TransactionHistoryServiceForSell transactionHistoryService;

    public P2PSellController(MerchantService merchantService, TransactionHistoryServiceForSell transactionHistoryService) {
        this.merchantService = merchantService;
        this.transactionHistoryService = transactionHistoryService;
    }


    @GetMapping
    public String showP2PSellPage(Model model)
    {
        List<Merchant> merchant = merchantService.getAllMerchants();
        List<TransactionHistory> transaction = transactionHistoryService.getAllBuyTransactions();

        model.addAttribute("merchantList", merchant);
        model.addAttribute("transactionList", transaction);
        return "p2psell";
    }

    @PostMapping("/sell")
    public String handleByRequest(@RequestParam("merchantId") String merchantId, @RequestParam("amount") double amount)
    {
        Merchant merchant = merchantService.getMerchantById(merchantId).orElse(null);
        if (merchant == null)
        {
            return "redirect:/p2psell?error=merchantNotFound";
        }

        TransactionHistoryForSell transactionHistory = new TransactionHistoryForSell();
        transactionHistory.setType("Sell");
        transactionHistory.setMerchantName(merchant.getName());
        transactionHistory.setAmount(amount);
        transactionHistory.setPaymentMethod(merchant.getPaymentMethod());
        transactionHistory.setStatus("Completed");
        transactionHistory.setDate(LocalDateTime.now());
        transactionHistoryService.saveTransactionForSell(transactionHistory);

        return "redirect:/p2psell?success=true";
    }

}
