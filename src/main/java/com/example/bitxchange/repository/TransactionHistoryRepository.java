package com.example.bitxchange.repository;


import com.example.bitxchange.model.TransactionHistory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends MongoRepository<TransactionHistory, String>
{
    List<TransactionHistory> findByTypeOrderByDateDesc(String type);
}
