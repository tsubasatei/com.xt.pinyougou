package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.Brand;

import java.util.List;
import java.util.Map;

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

    // 读取品牌列表
    List<Map<String, Object>> selectOptionList();
}
