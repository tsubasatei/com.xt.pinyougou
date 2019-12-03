package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.*;
import com.xt.pinyougou.pojo.*;
import com.xt.pinyougou.pojogroup.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Transactional
    @Override
    public void add(GoodsGroup goodsGroup) {
        // 商品
        Goods goods = goodsGroup.getGoods();
        goods.setAuditStatus("0");//设置未申请状态
        baseMapper.insert(goods);
        Long id = goods.getId();

        // 商品描述
        GoodsDesc goodsDesc = goodsGroup.getGoodsDesc();
        goodsDesc.setGoodsId(id);
        goodsDescMapper.insert(goodsDesc);

        saveItemList(goodsGroup);


    }

    // 插入商品SKU列表数据
    private void saveItemList(GoodsGroup goodsGroup) {
        Goods goods = goodsGroup.getGoods();
        GoodsDesc goodsDesc = goodsGroup.getGoodsDesc();

        if ("1".equals(goods.getIsEnableSpec())) {
            // 商品SKU列表
            List<Item> itemList = goodsGroup.getItemList();
            for (Item item : itemList) {
                // 标题
                String title = goods.getGoodsName();
                Map<String, Object> map = JSON.parseObject(item.getSpec());
                for (String key : map.keySet()) {
                    title += " " + map.get(key);
                }
                item.setTitle(title);
                setItemValues(goods, goodsDesc, item);
                itemMapper.insert(item);
            }
        } else {
            Item item = new Item();
            item.setTitle(goods.getGoodsName());
            setItemValues(goods, goodsDesc, item);
            item.setPrice( goods.getPrice());//价格
            item.setStatus("1"); //状态
            item.setIsDefault("1"); //是否默认
            item.setNum(99999); //库存数量
            item.setSpec("{}");

            itemMapper.insert(item);
        }
    }

    @Transactional
    @Override
    public void update(GoodsGroup goodsGroup) {
        baseMapper.updateById(goodsGroup.getGoods());
        goodsDescMapper.updateById(goodsGroup.getGoodsDesc());

        // 先删除原 SKU 列表
        itemMapper.delete(new QueryWrapper<Item>().lambda().eq(Item::getGoodsId, goodsGroup.getGoods().getId()));
        saveItemList(goodsGroup);
    }

    @Transactional(readOnly = true)
    @Override
    public GoodsGroup findOne(Long id) {
        GoodsGroup goodsGroup = new GoodsGroup();

        // 商品基本信息
        Goods goods = baseMapper.selectById(id);
        goodsGroup.setGoods(goods);

        // 商品描述
        GoodsDesc goodsDesc = goodsDescMapper.selectById(id);
        goodsGroup.setGoodsDesc(goodsDesc);

        // SKU 列表
        List<Item> items = itemMapper.selectList(new QueryWrapper<Item>().lambda().eq(Item::getGoodsId, goods.getId()));
        goodsGroup.setItemList(items);

        return goodsGroup;
    }

    @Transactional
    @Override
    public void updateStatus(Long[] ids, String status) {
        List<Goods> goodsList = baseMapper.selectBatchIds(Arrays.asList(ids));
        goodsList.forEach(goods -> goods.setAuditStatus(status));
        for (Goods goods : goodsList) {
            baseMapper.updateById(goods);
        }
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Transactional(readOnly = true)
    @Override
    public IPage<Goods> selectPage(Integer currentPage, Integer pageNum, Goods goods) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(goods.getSellerId())) {
            queryWrapper.lambda().eq(Goods::getSellerId, goods.getSellerId());
        }
        if (!StringUtils.isEmpty(goods.getAuditStatus())) {
            queryWrapper.lambda().eq(Goods::getAuditStatus, goods.getAuditStatus());
        }
        if (!StringUtils.isEmpty(goods.getGoodsName())) {
            queryWrapper.lambda().like(Goods::getGoodsName, goods.getGoodsName());
        }
        IPage<Goods> page = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return page;
    }

    private void setItemValues(Goods goods, GoodsDesc goodsDesc, Item item) {

        // 商品 SPU 编号
        item.setGoodsId(goods.getId());

        //商家编号
        item.setSellerId(goods.getSellerId());

        //商品分类编号（3级）
        item.setCategoryId(goods.getCategory3Id());

        // 分类名称
        ItemCat itemCat = itemCatMapper.selectById(goods.getCategory3Id());
        item.setCategory(itemCat.getName());

        // 创建日期
        item.setCreateTime(LocalDateTime.now());

        // 修改日期
        item.setUpdateTime(LocalDateTime.now());

        // 品牌名称
        Brand brand = brandMapper.selectById(goods.getBrandId());
        item.setBrand(brand.getName());

        //商家名称
        Seller seller = sellerMapper.selectById(goods.getSellerId());
        item.setSeller(seller.getNickName());

        //图片地址（取spu的第一个图片）
        List<Map> imagesList = JSON.parseArray(goodsDesc.getItemImages(), Map.class);
        if (imagesList.size() > 0) {
            item.setImage((String) imagesList.get(0).get("url"));
        }
    }

}
