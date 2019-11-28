package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.ItemCat;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
public interface ItemCatService extends IService<ItemCat> {

    /**
     * 根据上级ID返回列表
     * @param parentId : 父 ID
     * @return
     */
    List<ItemCat> listByParentId(Long parentId);

    Boolean deleteBatch(List<Long> asList);
}
