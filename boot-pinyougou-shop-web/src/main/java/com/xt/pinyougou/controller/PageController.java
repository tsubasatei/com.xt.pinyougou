package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PageController {

    // 首页
    @GetMapping("/index.html")
    public String index() {
        return "admin/index";
    }

    // 新增商品
    @GetMapping("/goods_edit.html")
    public String goodsEdit() {
        return "admin/goods_edit";
    }

    // 商品管理
    @GetMapping("/goods.html")
    public String goods() {
        return "admin/goods";
    }
}
