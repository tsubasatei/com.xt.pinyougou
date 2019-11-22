package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.mapper.SpecificationMapper;
import com.xt.pinyougou.mapper.SpecificationOptionMapper;
import com.xt.pinyougou.pojo.Specification;
import com.xt.pinyougou.pojo.SpecificationOption;
import com.xt.pinyougou.pojogroup.SpecificationAndOption;
import com.xt.pinyougou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-11-22
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements SpecificationService {

    @Autowired
    private SpecificationOptionServiceImpl optionService;

    @Transactional(readOnly = true)
    @Override
    public IPage<Specification> selectPage(Integer currentPage, Integer pageNum, Specification specification) {
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(specification.getSpecName())) {
            queryWrapper.lambda().like(Specification::getSpecName, specification.getSpecName());
        }
        IPage<Specification> page = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return page;
    }

    @Transactional
    @Override
    public boolean saveGroup(SpecificationAndOption specificationAndOption) {

        // 规格
        Specification specification = specificationAndOption.getSpecification();
        // 规格选项
        List<SpecificationOption> specificationOptions = specificationAndOption.getSpecificationOptions();

        baseMapper.insert(specification);
        Long spedId = specification.getId();

        for (SpecificationOption option : specificationOptions) {
            option.setSpecId(spedId);
        }
        boolean flag = optionService.saveBatch(specificationOptions);
        return flag;
    }

    @Transactional(readOnly = true)
    @Override
    public SpecificationAndOption findOne(Long id) {
        SpecificationAndOption specificationAndOption = new SpecificationAndOption();

        // 规格
        Specification specification = baseMapper.selectById(id);
        specificationAndOption.setSpecification(specification);

        // 规格选项集合
        QueryWrapper<SpecificationOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecificationOption::getSpecId, specification.getId());
        List<SpecificationOption> optionList = optionService.list(queryWrapper);
        specificationAndOption.setSpecificationOptions(optionList);

        return specificationAndOption;
    }

    @Transactional
    @Override
    public boolean updateGroup(SpecificationAndOption specificationAndOption) {
        // 规格
        Specification specification = specificationAndOption.getSpecification();
        // 规格选项
        List<SpecificationOption> specificationOptions = specificationAndOption.getSpecificationOptions();
        baseMapper.updateById(specification);

        // 规格Id
        Long id = specification.getId();

        // 先删除原来规格对象的规格选项集合
        QueryWrapper<SpecificationOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecificationOption::getSpecId, specification.getId());
        optionService.remove(queryWrapper);

        // 再保存新的规格列表
        for (SpecificationOption option : specificationOptions) {
            option.setSpecId(id);
        }
        boolean flag = optionService.saveBatch(specificationOptions);
        return flag;
    }

    @Transactional
    @Override
    public boolean deleteGroup(Integer id) {
        // 实体
        Specification specification = baseMapper.selectById(id);
        baseMapper.deleteById(id);
        QueryWrapper<SpecificationOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SpecificationOption::getSpecId, specification.getId());
        boolean flag = optionService.remove(queryWrapper);
        return flag;
    }

    @Transactional
    @Override
    public boolean deleteGroupBatch(List<Integer> ids) {
        baseMapper.deleteBatchIds(ids);
        QueryWrapper<SpecificationOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SpecificationOption::getSpecId, ids);
        boolean flag = optionService.remove(queryWrapper);
        return flag;
    }
}
