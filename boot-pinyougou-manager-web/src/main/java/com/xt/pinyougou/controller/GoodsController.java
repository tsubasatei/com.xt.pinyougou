package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.Goods;
import com.xt.pinyougou.pojogroup.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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

    @PostMapping("/page")
    public IPage<Goods> list(@RequestParam Integer currentPage, @RequestParam Integer pageNum, @RequestBody Goods goods) {
        IPage<Goods> page = goodsService.selectPage(currentPage, pageNum, goods);
        return page;
    }

    @GetMapping("/{id}")
    public GoodsGroup findOne(@PathVariable Long id) {
        return goodsService.findOne(id);
    }

    @GetMapping("updateStatus/{ids}/{status}")
    public Result findOne(@PathVariable("ids") Long[] ids, @PathVariable("status") String status) {
        try {
            goodsService.updateStatus(ids, status);
            return new Result(true, "状态更新成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@PathVariable Long[] ids) {
        try {
            goodsService.deleteBatch(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }
}

