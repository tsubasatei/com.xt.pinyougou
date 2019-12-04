package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.content.service.ContentService;
import com.xt.pinyougou.pojo.Content;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  广告前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    // 新增
    @PostMapping
    public Result save(@RequestBody Content content) {
        try {
            contentService.save(content);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    // 更新
    @PutMapping
    public Result update(@RequestBody Content content) {
        try {
            contentService.updateById(content);
            return new Result(true, "更新成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            contentService.removeById(id);
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
            contentService.removeByIds(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    // 查询
    @GetMapping("/{id}")
    public Content findOne(@PathVariable Long id){
        Content content = contentService.getById(id);
        return content;
    }

    // 查询列表
    @GetMapping("/list")
    public List<Content> list() {
        List<Content> list = contentService.list();
        return list;
    }

    /**
     * 分页条件查询
     */
    @PostMapping("/page")
    public IPage<Content> list(Integer currentPage, Integer pageNum, @RequestBody Content content) {
        IPage<Content> page = contentService.contentService(currentPage, pageNum, content);
        return page;
    }
}

