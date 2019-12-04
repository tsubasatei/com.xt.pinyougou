package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.content.service.ContentCategoryService;
import com.xt.pinyougou.pojo.ContentCategory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  广告分类前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    // 新增
    @PostMapping
    public Result save(@RequestBody ContentCategory contentCategory) {
        try {
            contentCategoryService.save(contentCategory);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    // 更新
    @PutMapping
    public Result update(@RequestBody ContentCategory contentCategory) {
        try {
            contentCategoryService.updateById(contentCategory);
            return new Result(true, "更新成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            contentCategoryService.removeById(id);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@PathVariable Long[] ids) {
        try {
            contentCategoryService.removeByIds(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    // 查询
    @GetMapping("/{id}")
    public ContentCategory findOne(@PathVariable Long id){
        ContentCategory contentCategory = contentCategoryService.getById(id);
        return contentCategory;
    }

    // 查询列表
    @GetMapping("/list")
    public List<ContentCategory> list() {
        List<ContentCategory> list = contentCategoryService.list();
        return list;
    }

    /**
     * 分页条件查询
     */
    @PostMapping("/page")
    public IPage<ContentCategory> list(Integer currentPage, Integer pageNum, @RequestBody ContentCategory contentCategory) {
        IPage<ContentCategory> page = contentCategoryService.contentCategoryService(currentPage, pageNum, contentCategory);
        return page;
    }
}

