package com.xt.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.pinyougou.content.service.ContentService;
import com.xt.pinyougou.pojo.Content;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    /**
     * 根据广告分类ID查询广告列表
     */
    @GetMapping("/findByCategoryId/{categoryId}")
    public List<Content> findByCategoryId(@PathVariable Long categoryId) {
        return contentService.findByCategoryId(categoryId);
    }

}
