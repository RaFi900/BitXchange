package com.example.bitxchange.controller;

import com.example.bitxchange.interfac.UserSignupInterface;

import com.example.bitxchange.model.UserSignup;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignupController
{
    private final UserSignupInterface userSignupService;

    public UserSignupController(UserSignupInterface userSignupService) {
        this.userSignupService = userSignupService;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model)
    {
        model.addAttribute("user" , new UserSignup());
        return "signup";
    }
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") @Valid UserSignup userSignup , BindingResult result, Model model)
    {
        if (!userSignup.getEmail().contains("@"))
        {
            result.rejectValue("email", "email.invalid", "Invalid email address");
        }
        if (result.hasErrors())
        {
            return "signup";
        }

        String response = userSignupService.registerUser(userSignup);

        // Show error if email or username already in use
        if (response.contains("already in use")) {
            model.addAttribute("signupError", response);
            return "signup";
        }

        return "redirect:/login";
    }
}
