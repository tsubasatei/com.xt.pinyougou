package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.pojo.Brand;
import com.xt.pinyougou.mapper.BrandMapper;
import com.xt.pinyougou.service.BrandService;
import org.springframework.util.StringUtils;

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

    @Override
    public IPage<Brand> selectPage(Integer pageSize, Integer pageNum, Brand brand) {
        IPage<Brand> result;

        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brand.getName())) {
//            queryWrapper.like("name", brand.getName());
            queryWrapper.lambda().like(Brand::getName, brand.getName());
        }
        if (!StringUtils.isEmpty(brand.getFirstChar())) {
//            queryWrapper.like("first_char", brand.getFirstChar());
            queryWrapper.lambda().like(Brand::getFirstChar, brand.getFirstChar());
        }
        result = baseMapper.selectPage(new Page<>(pageSize, pageNum), queryWrapper);


        return result;

    }
}
