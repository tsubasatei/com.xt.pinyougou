package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.entity.Result;
import com.xt.pinyougou.pojogroup.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  商品前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @PostMapping
    public Result add(@RequestBody GoodsGroup goodsGroup) {
        try {
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            goodsGroup.getGoods().setSellerId(sellerId);
            goodsService.add(goodsGroup);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }

    }
}

