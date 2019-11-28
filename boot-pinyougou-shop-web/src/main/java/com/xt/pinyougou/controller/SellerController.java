package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.entity.Result;
import com.xt.pinyougou.pojo.Seller;
import com.xt.pinyougou.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 新增
    @PostMapping
    public Result save(@RequestBody Seller seller) {
        Result result = new Result();
        try {
            seller.setStatus("0");
            String pwd = seller.getPassword();
            seller.setPassword(passwordEncoder.encode(pwd));
            seller.setCreateTime(LocalDateTime.now());
            boolean flag = sellerService.save(seller);
            if (flag) {
                result.setSuccess(true);
                result.setMessage("注册成功！");
            } else {
                result.setSuccess(false);
                result.setMessage("注册失败！");
            }
        } catch (Exception e) {
            result.setSuccess(true);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

