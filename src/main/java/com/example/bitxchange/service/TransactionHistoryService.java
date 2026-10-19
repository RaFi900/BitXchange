package com.example.bitxchange.service;

import com.example.bitxchange.model.TransactionHistory;
import com.example.bitxchange.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService
{
  private final TransactionHistoryRepository transactionHistoryRepository;

  @Autowired
    public  TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository)
    {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public TransactionHistory saveTransaction(TransactionHistory transactionHistory)
    {
        return transactionHistoryRepository.save(transactionHistory);
    }

    public List<TransactionHistory> getAllBuyTransactions()
    {
        return transactionHistoryRepository.findByTypeOrderByDateDesc("Buy");
    }
}
