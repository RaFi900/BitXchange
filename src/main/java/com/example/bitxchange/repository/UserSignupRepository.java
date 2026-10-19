package com.example.bitxchange.repository;


import com.example.bitxchange.model.UserSignup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSignupRepository extends MongoRepository<UserSignup, String>
{
  Optional<UserSignup> findByEmail(String email);
  Optional<UserSignup> findByName(String name);
  boolean existsByEmail(String email);
  boolean existsByName(String name);

}
