package com.example.bitxchange.service;

import com.example.bitxchange.model.TradeOrder;
import com.example.bitxchange.repository.TradeOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeOrderService
{
   private TradeOrderRepository tradeOrderRepository;

   public TradeOrderService(TradeOrderRepository tradeOrderRepository)
   {
       this.tradeOrderRepository = tradeOrderRepository;
   }

   public void saveOrder(TradeOrder order)
   {
       tradeOrderRepository.save(order);
   }

   public List<TradeOrder> getAllOrders()
   {
       return tradeOrderRepository.findAll();
   }

   public List<TradeOrder> getOrdersByType(String type)
   {
       return tradeOrderRepository.findByType(type);
   }
}
