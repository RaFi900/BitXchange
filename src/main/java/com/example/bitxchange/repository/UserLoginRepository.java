package com.example.bitxchange.repository;

import com.example.bitxchange.model.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String>
{

    Optional<UserLogin> findByEmail(String email);
}
