package com.example.bitxchange.repository;

import com.example.bitxchange.model.Coin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends MongoRepository<Coin, String>
{

}
