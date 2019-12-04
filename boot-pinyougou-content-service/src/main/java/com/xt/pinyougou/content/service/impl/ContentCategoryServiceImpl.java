package com.xt.pinyougou.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.content.service.ContentCategoryService;
import com.xt.pinyougou.mapper.ContentCategoryMapper;
import com.xt.pinyougou.pojo.ContentCategory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {
    @Transactional(readOnly = true)
    @Override
    public IPage<ContentCategory> contentCategoryService(Integer currentPage, Integer pageNum, ContentCategory contentCategory) {
        QueryWrapper<ContentCategory> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(contentCategory.getName())) {
            queryWrapper.lambda().like(ContentCategory::getName, contentCategory.getName());
        }
        IPage<ContentCategory> page = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return page;
    }
}
