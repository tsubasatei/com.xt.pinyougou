package com.xt.pinyougou.content.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.ContentCategory;

/**
 * <p>
 * 广告分类 服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
public interface ContentCategoryService extends IService<ContentCategory> {

    IPage<ContentCategory> contentCategoryService(Integer currentPage, Integer pageNum, ContentCategory contentCategory);
}
