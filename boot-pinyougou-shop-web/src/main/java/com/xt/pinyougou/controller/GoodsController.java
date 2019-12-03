package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.Goods;
import com.xt.pinyougou.pojogroup.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
            goodsGroup.getGoods().setIsMarketable("1");
            goodsService.add(goodsGroup);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @PutMapping
    public Result update(@RequestBody GoodsGroup goodsGroup) {
        try {
            // 获取当前登录的商家 ID
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();

            // 校验是否是当前商家的 id
            Goods goods = goodsService.getById(goodsGroup.getGoods().getId());

            //如果传递过来的商家 ID 并不是当前登录的用户的 ID, 则属于非法操作
            if (!goods.getSellerId().equals(sellerId) || !goodsGroup.getGoods().getSellerId().equals(sellerId)) {
                return new Result(false, "非法操作");
            }
            goodsService.update(goodsGroup);
            return new Result(true, "更新成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @PostMapping("/page")
    public IPage<Goods> list(@RequestParam Integer currentPage, @RequestParam Integer pageNum, @RequestBody Goods goods) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(name);
        IPage<Goods> page = goodsService.selectPage(currentPage, pageNum, goods);
        return page;
    }

    @GetMapping("/{id}")
    public GoodsGroup findOne(@PathVariable Long id) {
        return goodsService.findOne(id);
    }

    @GetMapping("/updateMarketable/{id}/{marketable}")
    public Result findOne(@PathVariable Long id, @PathVariable String marketable) {

        try {
            Goods goods = goodsService.getById(id);
            goods.setIsMarketable(marketable);
            goodsService.updateById(goods);
            if ("1".equals(marketable)) {
                return new Result(true, "上架成功");
            }else  if ("0".equals(marketable)) {
                return new Result(true, "下架成功");
            }
            return new Result(false, "更改失败");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

}

