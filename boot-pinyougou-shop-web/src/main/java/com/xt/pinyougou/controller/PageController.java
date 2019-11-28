package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 登录
    @GetMapping({"/", "/login"})
    public String login() {
        return "shoplogin";
    }

    // 注册
    @GetMapping("/register.html")
    public String register() {
        return "register";
    }

    // 首页
    @GetMapping("/admin/index")
    public String index() {
        return "admin/index";
    }
}
