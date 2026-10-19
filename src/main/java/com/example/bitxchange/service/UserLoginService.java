package com.example.bitxchange.service;

import com.example.bitxchange.model.UserLogin;
import com.example.bitxchange.model.UserSignup;
import com.example.bitxchange.repository.UserLoginRepository;
import com.example.bitxchange.repository.UserSignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService
{
  private final UserLoginRepository userLoginRepository;
  private final UserSignupRepository userSignupRepository;

  @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository,  UserSignupRepository userSignupRepository)
  {
      this.userLoginRepository = userLoginRepository;
      this.userSignupRepository = userSignupRepository;
  }

  public boolean validateLogin(String email, String password)
  {
      Optional<UserSignup> user = userSignupRepository.findByEmail(email);

      return user.isPresent() && user.get().getPassword().equals(password);
  }

  public void logLoginAttempt(UserLogin userLogin)
  {
      userLoginRepository.save(userLogin);
  }


}
