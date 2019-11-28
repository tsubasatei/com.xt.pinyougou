package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.Seller;
import com.xt.pinyougou.service.SellerService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  商家前端控制器
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

     //查询
    @GetMapping("/{id}")
    public Seller findOne(@PathVariable String id){
        Seller seller = sellerService.getById(id);
        return seller;
    }

    // 查询列表
    @GetMapping("/updateStatus/{sellerId}/{status}")
    public Result updateStatus(@PathVariable("sellerId") String sellerId, @PathVariable("status") String status) {
        try {
            sellerService.updateStatus(sellerId, status);
            return new Result(true, "成功");
        } catch (Exception e) {
            return new Result(false, "失败");
        }
    }

    /**
     * 分页条件查询
     * Mybatis-Plus3 的 QueryWrapper 不支持 dubbo 序列化，自己写一个 service 方法
     * @param currentPage
     * @param pageNum
     * @param seller
     * @return
     */
    @PostMapping("/page")
    public IPage<Seller> list(Integer currentPage, Integer pageNum, @RequestBody Seller seller) {
        IPage<Seller> page = sellerService.selectPage(currentPage, pageNum, seller);
        return page;
    }
}

