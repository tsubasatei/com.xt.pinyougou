package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/login"})
    public String login() {
        return "shoplogin";
    }

    @GetMapping("/register.html")
    public String register() {
        return "register";
    }
}
