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

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  品牌：服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-20
 */
@Service(timeout = 5000)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public IPage<Brand> selectPage(Integer pageSize, Integer pageNum, Brand brand) {

        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brand.getName())) {
//            queryWrapper.like("name", brand.getName());
            queryWrapper.lambda().like(Brand::getName, brand.getName());
        }
        if (!StringUtils.isEmpty(brand.getFirstChar())) {
//            queryWrapper.like("first_char", brand.getFirstChar());
            queryWrapper.lambda().like(Brand::getFirstChar, brand.getFirstChar());
        }
        IPage<Brand>  result = baseMapper.selectPage(new Page<>(pageSize, pageNum), queryWrapper);
        return result;

    }

    @Override
    public List<Map<String, Object>> selectOptionList() {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name as text");
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        return maps;
    }
}
