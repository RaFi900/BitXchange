package com.example.bitxchange.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

   @GetMapping("/profile")
   public String showProfilePage(Model model, HttpSession session)
   {
       String userEmail = (String) session.getAttribute("userEmail");
       if (userEmail == null)
       {
           return "redirect:/login";
       }
       return "profile";
   }



    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // invalidate user session
        return "redirect:/login"; // redirect to login page
    }
}
