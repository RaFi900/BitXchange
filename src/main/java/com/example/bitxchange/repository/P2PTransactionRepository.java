package com.example.bitxchange.repository;

import com.example.bitxchange.model.P2PTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface P2PTransactionRepository extends MongoRepository<P2PTransaction, String>
{

    List<P2PTransaction> findByUserEmail(String userEmail);
}
