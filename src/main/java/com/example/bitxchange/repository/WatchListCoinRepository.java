package com.example.bitxchange.repository;

import com.example.bitxchange.model.WatchListCoin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchListCoinRepository extends MongoRepository<WatchListCoin, String>
{
   List<WatchListCoin> findByUserEmail(String userEmail);
   void deleteBySymbolAndUserEmail(String symbol, String userEmail);

}
