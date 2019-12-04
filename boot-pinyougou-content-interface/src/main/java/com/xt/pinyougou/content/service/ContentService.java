package com.xt.pinyougou.content.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.Content;

import java.util.List;

/**
 * <p>
 *  广告内容服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-27
 */
public interface ContentService extends IService<Content> {

    IPage<Content> contentService(Integer currentPage, Integer pageNum, Content content);

    /**
     * 根据广告类型ID查询列表
     * @param categoryId 广告分类
     * @return
     */
    List<Content> findByCategoryId(Long categoryId);

}
