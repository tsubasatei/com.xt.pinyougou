package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.Goods;
import com.xt.pinyougou.pojogroup.GoodsGroup;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
public interface GoodsService extends IService<Goods> {

    void add(GoodsGroup goodsGroup);

    void update(GoodsGroup goodsGroup);

    IPage<Goods> selectPage(Integer currentPage, Integer pageNum, Goods goods);

    GoodsGroup findOne(Long id);

    void updateStatus(Long[] ids, String status);

    void deleteBatch(List<Long> asList);
}
