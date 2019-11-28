package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.TypeTemplate;
import com.xt.pinyougou.service.TypeTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  模板管理：前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    
    @Reference
    private TypeTemplateService typeTemplateService;

    /**
     * 读取类型模板列表
     * @return
     */
    @GetMapping("/findOptionList")
    public List<Map<String, Object>> findOptionList() {
        List<Map<String, Object>> list = typeTemplateService.findOptionList();
        return list;
    }

    // 新增
    @PostMapping
    public Result save(@RequestBody TypeTemplate typeTemplate) {
        Result result = new Result();
        try {
            // 保存组
            boolean flag = typeTemplateService.save(typeTemplate);
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
    public Result update(@RequestBody TypeTemplate typeTemplate) {
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.updateById(typeTemplate);
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
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.removeById(id);
            if (flag) {
                result.setSuccess(true);
                result.setMessage("删除成功！");
            } else {
                result.setSuccess(false);
                result.setMessage("删除失败！");
            }
        } catch (Exception e) {
            result.setSuccess(true);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 批量删除
     * 不写 @ResponseBody, 会报如下错误
     * org.thymeleaf.exceptions.TemplateInputException:
     * Error resolving template [deleteBatch/22], template might not exist or might not be accessible by any of the configured Template Resolvers
     */
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@PathVariable Integer[] ids) {
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.removeByIds(Arrays.asList(ids));
            if (flag) {
                result.setSuccess(true);
                result.setMessage("删除成功！");
            } else {
                result.setSuccess(false);
                result.setMessage("删除失败！");
            }
        } catch (Exception e) {
            result.setSuccess(true);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    // 查询
    @GetMapping("/{id}")
    public TypeTemplate findOne(@PathVariable Long id){
        TypeTemplate typeTemplate = typeTemplateService.getById(id);
        return typeTemplate;
    }

    // 查询列表
    @GetMapping("/typeTemplates")
    public List<TypeTemplate> list() {
        List<TypeTemplate> list = typeTemplateService.list();
        return list;
    }

    /**
     * 分页条件查询
     * Mybatis-Plus3 的 QueryWrapper 不支持 dubbo 序列化，自己写一个 service 方法
     * @param currentPage
     * @param pageNum
     * @param typeTemplate
     * @return
     */
    @PostMapping("/page")
    public IPage<TypeTemplate> list(Integer currentPage, Integer pageNum, @RequestBody TypeTemplate typeTemplate) {
        IPage<TypeTemplate> page = typeTemplateService.selectPage(currentPage, pageNum, typeTemplate);
        return page;
    }
}

