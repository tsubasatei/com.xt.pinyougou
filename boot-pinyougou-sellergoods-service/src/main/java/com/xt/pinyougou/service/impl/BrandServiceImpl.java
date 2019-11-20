package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.entity.Brand;
import com.xt.pinyougou.mapper.BrandMapper;
import com.xt.pinyougou.service.BrandService;

/**
 * <p>
 *  品牌：服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-20
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
