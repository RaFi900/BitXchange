package com.example.bitxchange.service;

import com.example.bitxchange.model.P2PTransaction;
import com.example.bitxchange.model.TradeOrder;
import com.example.bitxchange.repository.P2PTransactionRepository;
import com.example.bitxchange.repository.TradeOrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetCalculationService
{

    private final TradeOrderRepository tradeOrderRepository;
    private final P2PTransactionRepository p2PTransactionRepository;

    public AssetCalculationService(P2PTransactionRepository p2PTransactionRepository, TradeOrderRepository tradeOrderRepository)
    {
        this.p2PTransactionRepository = p2PTransactionRepository;
        this.tradeOrderRepository = tradeOrderRepository;
    }

    public String getTotalAssetValue(String userEmail)
    {
       BigDecimal totalValue = BigDecimal.ZERO;
        Map<String, BigDecimal> coinHolding = new HashMap<>();
        List<TradeOrder> tradeOrders = tradeOrderRepository.findByUserEmail(userEmail);
        for (TradeOrder order : tradeOrders)
        {
            String symbol = order.getSymbol();
            BigDecimal quantity = new BigDecimal(order.getQuantity());
            coinHolding.put(symbol,coinHolding.getOrDefault(symbol, BigDecimal.ZERO).add(order.getType().equalsIgnoreCase("Buy")?quantity:quantity.negate()));
        }
        RestTemplate restTemplate = new RestTemplate();
        for (Map.Entry<String ,BigDecimal> entry : coinHolding.entrySet())
        {
            String symbol = entry.getKey();
            BigDecimal quantity = entry.getValue();
            try
            {
                String url = "https://api.binance.com/api/v3/ticker/price?symbol=" + symbol + "USDT";
                String response = restTemplate.getForObject(url, String.class);
                JSONObject json = new JSONObject(response);
                BigDecimal price = new BigDecimal(json.getString("price"));

                totalValue = totalValue.add(quantity.multiply(price));
            }
            catch (Exception e)
            {
                System.out.println("Error fetching price for: " + symbol);
            }
        }
         List<P2PTransaction> p2PTransactions = p2PTransactionRepository.findByUserEmail(userEmail);
        BigDecimal usdtBalance = BigDecimal.ZERO;
        for (P2PTransaction tx : p2PTransactions)
        {
            BigDecimal amount = new BigDecimal(tx.getAmount());
            usdtBalance = usdtBalance.add(tx.getType().equalsIgnoreCase("Buy")?amount:amount.negate());
        }
        totalValue = totalValue.add(usdtBalance);
        return "$" + totalValue.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

    }
}

