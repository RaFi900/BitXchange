package com.example.bitxchange.interfac;

import com.example.bitxchange.model.UserSignup;

import java.util.Optional;

public  interface UserSignupInterface
{
   String registerUser(UserSignup userSignup);

   Optional<UserSignup> findByEmail(String email);

   Optional<UserSignup> findByName(String name);
}
