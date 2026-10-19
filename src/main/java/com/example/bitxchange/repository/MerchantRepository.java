package com.example.bitxchange.repository;

import com.example.bitxchange.model.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends MongoRepository<Merchant, String>
{

}
