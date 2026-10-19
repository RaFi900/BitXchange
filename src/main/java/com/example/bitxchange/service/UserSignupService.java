package com.example.bitxchange.service;

import com.example.bitxchange.interfac.UserSignupInterface;
import com.example.bitxchange.model.UserSignup;
import com.example.bitxchange.repository.UserSignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSignupService implements UserSignupInterface
{
    private final UserSignupRepository userSignupRepository;

    @Autowired
   public UserSignupService(UserSignupRepository userSignupRepository)
   {
       this.userSignupRepository = userSignupRepository;
   }

   @Override
   public String registerUser (UserSignup userSignup)
   {
      if (userSignupRepository.existsByEmail(userSignup.getEmail()))
          return "Email is already in use!";
      else if (userSignupRepository.existsByName(userSignup.getName()))
           return "UserName is already in use!";
      else
      {
         userSignupRepository.save(userSignup);
         return "User registered successfully!";
      }
   }
   @Override
   public Optional<UserSignup> findByEmail(String email)
   {
       return userSignupRepository.findByEmail(email);
   }

   @Override
  public Optional<UserSignup> findByName(String name)
  {
      return userSignupRepository.findByName(name);
  }
}
