package com.xt.pinyougou.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.Brand;
import com.xt.pinyougou.pojo.Specification;
import com.xt.pinyougou.pojogroup.SpecificationAndOption;
import com.xt.pinyougou.service.SpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  规格管理：前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-22
 */
@RestController
public class SpecificationController {

    @Reference(timeout = 5000)
    private SpecificationService specificationService;

    // 新增
    @PostMapping("/specification")
    public Result save(@RequestBody SpecificationAndOption specificationAndOption) {
        Result result = new Result();
        try {
            // 保存组
            boolean flag = specificationService.saveGroup(specificationAndOption);
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
    @PutMapping("/specification")
    public Result update(@RequestBody SpecificationAndOption specificationAndOption) {
        Result result = new Result();
        try {
            boolean flag = specificationService.updateGroup(specificationAndOption);
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
    @DeleteMapping("/specification/{id}")
    public Result delete(@PathVariable Integer id) {
        Result result = new Result();
        try {
            boolean flag = specificationService.deleteGroup(id);
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
    @ResponseBody
    @DeleteMapping("/specification/deleteBatch/{ids}")
    public Result deleteBatch(@PathVariable Integer[] ids) {
        Result result = new Result();
        try {
            boolean flag = specificationService.deleteGroupBatch(Arrays.asList(ids));
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
    @GetMapping("/specification/{id}")
    public SpecificationAndOption findOne(@PathVariable Long id){
        SpecificationAndOption specificationAndOption = specificationService.findOne(id);
        return specificationAndOption;
    }

    // 查询列表
    @GetMapping("/specifications")
    public List<Specification> list() {
        List<Specification> list = specificationService.list();
        return list;
    }

    /**
     * 分页条件查询
     * Mybatis-Plus3 的 QueryWrapper 不支持 dubbo 序列化，自己写一个 service 方法
     * @param currentPage
     * @param pageNum
     * @param specification
     * @return
     */
    @PostMapping("/specification/page")
    public IPage<Specification> list(Integer currentPage, Integer pageNum, @RequestBody Specification specification) {
        IPage<Specification> page = specificationService.selectPage(currentPage, pageNum, specification);
        return page;
    }
}

