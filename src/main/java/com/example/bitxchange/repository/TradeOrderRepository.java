package com.example.bitxchange.repository;

import com.example.bitxchange.model.TradeOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeOrderRepository extends MongoRepository<TradeOrder, String>
{
   List<TradeOrder> findByType(String type);

   List<TradeOrder> findByUserEmail(String userEmail);
}
