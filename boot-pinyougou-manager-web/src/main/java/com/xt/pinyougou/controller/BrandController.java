package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.pinyougou.entity.Brand;
import com.xt.pinyougou.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  品牌：前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @GetMapping("/list")
    public List<Brand> list() {
        System.out.println("查询品牌列表。。。。");
        List<Brand> list = brandService.list();
        return list;
    }
}

