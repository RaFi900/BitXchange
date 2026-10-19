package com.example.bitxchange.controller;

import com.example.bitxchange.model.UserLogin;
import com.example.bitxchange.service.UserLoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController
{
  private final UserLoginService userLoginService;

  @Autowired
  public UserLoginController(UserLoginService userLoginService)
  {
      this.userLoginService = userLoginService;
  }

  @GetMapping("/login")
  public String showLoginForm(Model model)
  {
     model.addAttribute("userLogin", new UserLogin());
     return "login";
  }

  @PostMapping("/login")
  public String processLogin(@ModelAttribute("userLogin") @Valid UserLogin userLogin, BindingResult result, Model model, HttpSession session)
  {
      if (result.hasErrors())
      {
         return "login";
      }
      boolean isValid = userLoginService.validateLogin(userLogin.getEmail(), userLogin.getPassword());

          if (isValid)
          {
              session.setAttribute("userEmail", userLogin.getEmail());
              return "redirect:/dashboard";
          }
          else
          {
              model.addAttribute("loginError", "Invalid email or password");
              return "login";
          }
  }
}
