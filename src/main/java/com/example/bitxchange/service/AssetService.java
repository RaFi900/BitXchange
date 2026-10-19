package com.example.bitxchange.service;

import com.example.bitxchange.model.AssetSnapshot;
import com.example.bitxchange.model.CoinHolding;
import com.example.bitxchange.model.P2PTransaction;
import com.example.bitxchange.model.TradeOrder;
import com.example.bitxchange.repository.AssetSnapshotRepository;
import com.example.bitxchange.repository.P2PTransactionRepository;
import com.example.bitxchange.repository.TradeOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetService
{
   private final TradeOrderRepository tradeOrderRepository;
   private final P2PTransactionRepository p2PTransactionRepository;
   private final AssetSnapshotRepository assetSnapshotRepository;

   public AssetService(TradeOrderRepository tradeOrderRepository, P2PTransactionRepository p2PTransactionRepository, AssetSnapshotRepository assetSnapshotRepository)
   {
       this.tradeOrderRepository = tradeOrderRepository;
       this.p2PTransactionRepository = p2PTransactionRepository;
       this.assetSnapshotRepository = assetSnapshotRepository;
   }

   public AssetSnapshot calculateCureentAssets()
   {
       List<TradeOrder> trades = tradeOrderRepository.findAll();
       List<P2PTransaction> p2PTransactions = p2PTransactionRepository.findAll();

       Map<String, Double> coinQuantitiesmap = new HashMap<>();
       double totalValue=0;

       for (TradeOrder order : trades)
       {
           String coin = order.getCoinName();
           double quantity = order.getQuantity();
           double amount = order.getTotalAmount();

           if (order.getType().equalsIgnoreCase("Buy"))
           {
               coinQuantitiesmap.put(coin, coinQuantitiesmap.getOrDefault(coin, 0.0) + quantity);
               totalValue += amount;
           }
           else if (order.getType().equalsIgnoreCase("Sell"))
           {
              coinQuantitiesmap.put(coin, coinQuantitiesmap.getOrDefault(coin, 0.0) - quantity);
              totalValue -= amount;
           }
       }

       for (P2PTransaction tx : p2PTransactions)
       {
           if (tx.getType().equalsIgnoreCase("Buy"))
           {
               coinQuantitiesmap.put("USDT", coinQuantitiesmap.getOrDefault("USDT", 0.0) + tx.getAmount());
               totalValue += tx.getAmount();
           }
           else if (tx.getType().equalsIgnoreCase("Sell"))
           {
               coinQuantitiesmap.put("USDT", coinQuantitiesmap.getOrDefault("USDT", 0.0) - tx.getAmount());
               totalValue -= tx.getAmount();
           }
       }

       List<CoinHolding> coinHoldings = new ArrayList<>();
       for (Map.Entry<String, Double> entry : coinQuantitiesmap.entrySet())
       {
           if (entry.getValue() > 0.0)
           {
               coinHoldings.add(new CoinHolding(entry.getKey(), entry.getValue(), entry.getValue()));
           }
       }

       AssetSnapshot snapshot = new AssetSnapshot(totalValue, coinHoldings);

       assetSnapshotRepository.save(snapshot);
       return snapshot;
   }

       public AssetSnapshot getLatestSnapshot()
       {
           return assetSnapshotRepository.findTopByOrderByDateDesc();
       }
}
