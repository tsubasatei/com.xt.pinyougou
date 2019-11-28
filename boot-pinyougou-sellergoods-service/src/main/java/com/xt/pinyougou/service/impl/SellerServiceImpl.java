package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.SellerMapper;
import com.xt.pinyougou.pojo.Seller;
import com.xt.pinyougou.service.SellerService;

/**
 * <p>
 *  商家服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@Service(timeout = 5000)
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = baseMapper.selectById(sellerId);
        seller.setStatus(status);
        baseMapper.updateById(seller);
    }

    @Override
    public IPage<Seller> selectPage(Integer currentPage, Integer pageNum, Seller seller) {
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Seller::getStatus, seller.getStatus());
        IPage<Seller> page = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return page;
    }
}
