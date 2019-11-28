package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.pinyougou.pojo.ItemCat;
import com.xt.pinyougou.service.ItemCatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    /**
     * 获取子类列表
     *
     */
    @GetMapping("/list/{parentId}")
    public List<ItemCat> listByParentId(@PathVariable Long parentId) {
        List<ItemCat> list = itemCatService.listByParentId(parentId);
        return list;
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ItemCat findOne(@PathVariable Long id) {
        ItemCat itemCat = itemCatService.getById(id);
        return itemCat;
    }

}

