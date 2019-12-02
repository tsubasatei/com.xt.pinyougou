package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.SpecificationOptionMapper;
import com.xt.pinyougou.mapper.TypeTemplateMapper;
import com.xt.pinyougou.pojo.SpecificationOption;
import com.xt.pinyougou.pojo.TypeTemplate;
import com.xt.pinyougou.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public IPage<TypeTemplate> selectPage(Integer currentPage, Integer pageNum, TypeTemplate typeTemplate) {

        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(typeTemplate.getName())) {
            queryWrapper.lambda().like(TypeTemplate::getName, typeTemplate.getName());
        }
        IPage<TypeTemplate>  result = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return result;
    }

    @Override
    public List<Map<String, Object>> findOptionList() {
        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name as text");
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        return maps;
    }

    @Override
    public List<Map> findSpecList(Long id) {
        // 查询模板
        TypeTemplate typeTemplate = baseMapper.selectById(id);
        List<Map> maps = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
        for (Map map : maps) {
            List<SpecificationOption> options = specificationOptionMapper.selectList(
                    new QueryWrapper<SpecificationOption>().lambda().eq(SpecificationOption::getSpecId, map.get("id")));
            map.put("options", options);
        }
        return maps;
    }
}
