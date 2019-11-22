package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制页面的跳转
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    // 品牌管理
    @GetMapping("/brand.html")
    public String brand() {
        return "admin/brand";
    }

    // 规格管理
    @GetMapping("/specification.html")
    public String specification() {
        return "admin/specification";
    }
}
