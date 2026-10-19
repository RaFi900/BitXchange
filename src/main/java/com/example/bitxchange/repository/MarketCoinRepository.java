package com.example.bitxchange.repository;


import com.example.bitxchange.model.MarketCoin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketCoinRepository extends MongoRepository<MarketCoin, String>
{
  List<MarketCoin> findBySymbolContainingIgnoreCase(String symbol);
}

