package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.ItemCat;
import com.xt.pinyougou.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    // 新增
    @PostMapping
    public Result save(@RequestBody ItemCat itemCat) {
        Result result = new Result();
        try {
            boolean flag = itemCatService.save(itemCat);
            if (flag) {
                result.setSuccess(true);
                result.setMessage("添加成功！");
            } else {
                result.setSuccess(false);
                result.setMessage("添加失败！");
            }
        } catch (Exception e) {
            result.setSuccess(true);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    // 更新
    @PutMapping
    public Result update(@RequestBody ItemCat itemCat) {
        Result result = new Result();
        try {
            boolean flag = itemCatService.updateById(itemCat);
            if (flag) {
                result.setSuccess(true);
                result.setMessage("更新成功！");
            } else {
                result.setSuccess(false);
                result.setMessage("更新失败！");
            }
        } catch (Exception e) {
            result.setSuccess(true);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            List<ItemCat> cats = itemCatService.listByParentId(id);
            if (cats == null || cats.isEmpty()) {
                itemCatService.removeById(id);
                return new Result(true, "删除成功！");
            }
            return new Result(false, "该商品分类下还有数据，暂不能删除！");
        } catch (Exception ex) {
            return new Result(false, ex.getMessage());
        }
    }

    // 删除
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@PathVariable Long[] ids) {
        try {
            Boolean flag = itemCatService.deleteBatch(Arrays.asList(ids));
            if (flag) {
                return new Result(true, "删除成功！");
            }
            return new Result(false, "该商品分类下还有数据，暂不能删除！");
        } catch (Exception ex) {
            return new Result(false, ex.getMessage());
        }
    }

    @GetMapping("/list")
    public List<ItemCat> list () {
        return itemCatService.list();
    }
}

