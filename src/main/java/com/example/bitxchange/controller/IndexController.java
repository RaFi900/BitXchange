package com.example.bitxchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String showHomePage(Model model) {
        // can be fetched and pass data
        return "index";
    }
}

