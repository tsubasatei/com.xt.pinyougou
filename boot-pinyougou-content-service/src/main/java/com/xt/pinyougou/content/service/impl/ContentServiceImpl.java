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
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Override
    public IPage<Content> contentService(Integer currentPage, Integer pageNum, Content content) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(content.getCategoryId())) {
            queryWrapper.lambda().eq(Content::getCategoryId, content.getCategoryId());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }

    @Override
    public List<Content> findByCategoryId(Long categoryId) {
        return baseMapper.selectList(
                new QueryWrapper<Content>().orderByAsc("sort_order")
                        .lambda()
                        .eq(Content::getCategoryId, categoryId)
                        .eq(Content::getStatus, "1")  // 开启状态
                );
    }
}
