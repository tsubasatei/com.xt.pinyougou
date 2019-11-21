package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.Brand;
import com.xt.pinyougou.service.BrandService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * <p>
 *  品牌：前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-20
 */
@RestController
//@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    // 新增
    @PostMapping("/brand")
    public Result save(Brand brand) {
        Result result = new Result();
        try {
            boolean flag = brandService.save(brand);
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
    @PutMapping("/brand")
    public Result update(Brand brand) {
        Result result = new Result();
        try {
            boolean flag = brandService.updateById(brand);
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
    @DeleteMapping("/brand/{id}")
    public Result delete(@PathVariable Integer id) {
        Result result = new Result();
        try {
            boolean flag = brandService.removeById(id);
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
    @GetMapping("/brand/{id}")
    public Brand findOne(@PathVariable Integer id){
        Brand brand = brandService.getById(id);
        return brand;
    }

    // 查询列表
    @GetMapping("/brands")
    public List<Brand> list() {
        List<Brand> list = brandService.list();
        return list;
    }

    /**
     * 分页条件查询
     * Mybatis-Plus3 的 QueryWrapper 不支持 dubbo 序列化，自己写一个 service 方法
     * @param pageSize
     * @param pageNum
     * @param brand
     * @return
     */
    @GetMapping("/brand/page")
    public IPage<Brand> list(@RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "10") Integer pageNum,
                             Brand brand) {
        IPage<Brand> page = brandService.selectPage(pageSize, pageNum, brand);
        return page;
    }

}

