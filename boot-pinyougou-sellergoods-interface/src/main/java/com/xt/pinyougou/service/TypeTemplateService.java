package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.TypeTemplate;

/**
 * <p>
 *  模板服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-22
 */
public interface TypeTemplateService extends IService<TypeTemplate> {

    IPage<TypeTemplate> selectPage(Integer currentPage, Integer pageNum, TypeTemplate typeTemplate);
}
