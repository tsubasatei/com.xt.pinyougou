package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.Seller;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
public interface SellerService extends IService<Seller> {

    IPage<Seller> selectPage(Integer currentPage, Integer pageNum, Seller seller);

    void updateStatus(String sellerId, String status);
}
