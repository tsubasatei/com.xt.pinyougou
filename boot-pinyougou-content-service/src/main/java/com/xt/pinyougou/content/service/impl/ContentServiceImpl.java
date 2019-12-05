package com.xt.pinyougou.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.content.service.ContentService;
import com.xt.pinyougou.mapper.ContentMapper;
import com.xt.pinyougou.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@CacheConfig(cacheNames = "content")
@Service(timeout = 5000)
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Transactional(readOnly = true)
    @Override
    public IPage<Content> contentService(Integer currentPage, Integer pageNum, Content content) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(content.getCategoryId())) {
            queryWrapper.lambda().eq(Content::getCategoryId, content.getCategoryId());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }


    @Cacheable
    @Override
    public List<Content> findByCategoryId(Long categoryId) {
        System.out.println("查询广告分类：" + categoryId);
        return baseMapper.selectList(
                new QueryWrapper<Content>().orderByAsc("sort_order")
                        .lambda()
                        .eq(Content::getCategoryId, categoryId)
                        .eq(Content::getStatus, "1")  // 开启状态
                );
    }

    @Transactional
    @Override
    public boolean updateById(Content content) {
        //查询修改前的分类 Id
        Long categoryId = super.getById(content.getId()).getCategoryId();
        Object old = redisTemplate.opsForValue().get("content::" + categoryId);
        System.out.println("更新前原缓存：" + old);
        redisTemplate.delete("content::" + categoryId);
        boolean flag = super.updateById(content);

        //如果分类 ID 发生了修改, 清除修改后的分类 ID 的缓存
        if(categoryId.longValue() != content.getCategoryId().longValue()){
            redisTemplate.delete(content.getCategoryId());
        }

        return flag;

    }

    @Transactional
    @Override
    public boolean save(Content content) {
        boolean flag = super.save(content);
        Object old = redisTemplate.opsForValue().get("content::" + content.getCategoryId());
        System.out.println("原缓存：" + old);
        redisTemplate.delete("content::" + content.getCategoryId());
        return flag;
    }

    @Transactional
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean flag;
        for (Serializable serializable : idList) {
            //清除缓存
            Long categoryId = super.getById(serializable).getCategoryId(); //广告分类ID
            redisTemplate.delete("content::" + categoryId);
            flag = super.removeById(serializable);
            if (flag) {
                continue;
            }
            return false;
        }
        return true;
    }
}
