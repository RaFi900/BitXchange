package com.example.bitxchange.repository;

import com.example.bitxchange.model.TransactionHistory;
import com.example.bitxchange.model.TransactionHistoryForSell;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepositoryForSell extends MongoRepository<TransactionHistoryForSell,String> {
    List<TransactionHistory> findByTypeOrderByDateDesc(String type);
}
