package com.example.bitxchange.service;

import com.example.bitxchange.model.TransactionHistory;
import com.example.bitxchange.model.TransactionHistoryForSell;
import com.example.bitxchange.repository.TransactionHistoryRepositoryForSell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryServiceForSell {

    private final TransactionHistoryRepositoryForSell transactionHistoryRepositoryForlSell;

    public TransactionHistoryServiceForSell(TransactionHistoryRepositoryForSell transactionHistoryRepositoryForlSell)
    {
        this.transactionHistoryRepositoryForlSell = transactionHistoryRepositoryForlSell;
    }

    public TransactionHistoryForSell saveTransactionForSell(TransactionHistoryForSell transactionHistory)
    {
        return transactionHistoryRepositoryForlSell.save(transactionHistory);
    }

    public List<TransactionHistory> getAllBuyTransactions()
    {
        return transactionHistoryRepositoryForlSell.findByTypeOrderByDateDesc("Sell");
    }
}
