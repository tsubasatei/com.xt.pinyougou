package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.TypeTemplateMapper;
import com.xt.pinyougou.pojo.TypeTemplate;
import com.xt.pinyougou.service.TypeTemplateService;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  模板服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-22
 */
@Service(timeout = 5000)
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateMapper, TypeTemplate> implements TypeTemplateService {

    @Override
    public IPage<TypeTemplate> selectPage(Integer currentPage, Integer pageNum, TypeTemplate typeTemplate) {

        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(typeTemplate.getName())) {
            queryWrapper.lambda().like(TypeTemplate::getName, typeTemplate.getName());
        }
        IPage<TypeTemplate>  result = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return result;
    }
}