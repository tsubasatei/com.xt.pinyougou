package com.xt.pinyougou.pojogroup;

import com.xt.pinyougou.pojo.Goods;
import com.xt.pinyougou.pojo.GoodsDesc;
import com.xt.pinyougou.pojo.Item;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品组合实体类
 */
@Data
public class GoodsGroup implements Serializable {
    /**
     * 商品
     */
    private Goods goods;

    /**
     * 商品描述
     */
    private GoodsDesc goodsDesc;

    /**
     * 商品SKU列表
     */
    private List<Item> itemList;
}
