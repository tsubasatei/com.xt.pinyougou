package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.GoodsDescMapper;
import com.xt.pinyougou.mapper.GoodsMapper;
import com.xt.pinyougou.pojo.Goods;
import com.xt.pinyougou.pojo.GoodsDesc;
import com.xt.pinyougou.pojogroup.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@Service(timeout = 5000)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsDescMapper goodsDescMapper;

    @Transactional
    @Override
    public void add(GoodsGroup goodsGroup) {
        Goods goods = goodsGroup.getGoods();
        GoodsDesc goodsDesc = goodsGroup.getGoodsDesc();

        goods.setAuditStatus("0");//设置未申请状态
        baseMapper.insert(goods);
        Long id = goods.getId();
        System.out.println(id);

        goodsDesc.setGoodsId(id);
        goodsDescMapper.insert(goodsDesc);
    }
}
