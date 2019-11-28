package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.ItemCatMapper;
import com.xt.pinyougou.pojo.ItemCat;
import com.xt.pinyougou.service.ItemCatService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@Service(timeout = 5000)
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

    @Override
    public List<ItemCat> listByParentId(Long parentId) {
        return baseMapper.selectList(new QueryWrapper<ItemCat>().lambda().eq(ItemCat::getParentId, parentId));
    }

    @Transactional
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            List<ItemCat> cats = listByParentId(id);
            if (cats == null || cats.isEmpty()) {
                baseMapper.deleteById(id);
            } else {
                return false;
            }
        }
        return true;
    }
}
