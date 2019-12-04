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

    // 家目录
    @GetMapping("/home.html")
    public String home() {
        return "admin/home";
    }

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

    // 模板管理
    @GetMapping("/type_template.html")
    public String typeTemplate() {
        return "admin/type_template";
    }

    // 商家审核
    @GetMapping("/seller_1.html")
    public String seller1() {
        return "admin/seller_1";
    }

    // 分类管理
    @GetMapping("/item_cat.html")
    public String itemCat() {
        return "admin/item_cat";
    }

    // 商品审核
    @GetMapping("/goods.html")
    public String goods() {
        return "admin/goods";
    }

    // 商品详情
    @GetMapping("/goods_detail.html")
    public String goodsDetail() {
        return "admin/goods_detail";
    }

    // 广告类型管理
    @GetMapping("/content_category.html")
    public String contentCategory() {
        return "admin/content_category";
    }

    // 广告管理
    @GetMapping("/content.html")
    public String content() {
        return "admin/content";
    }


}
