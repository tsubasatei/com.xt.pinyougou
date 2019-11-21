package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.Brand;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-20
 */
public interface BrandService extends IService<Brand> {

    // 分页查询
    IPage<Brand> selectPage(Integer pageSize, Integer pageNum, Brand brand);
}
