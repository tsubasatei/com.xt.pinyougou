package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.Specification;
import com.xt.pinyougou.pojogroup.SpecificationAndOption;

import java.util.List;

/**
 * <p>
 *  规格管理：服务类
 * </p>
 *
 * @author xt
 * @since 2019-11-22
 */
public interface SpecificationService extends IService<Specification> {

    // 条件分页查询
    IPage<Specification> selectPage(Integer currentPage, Integer pageNum, Specification specification);

    // 保存规格及规格选项
    boolean saveGroup(SpecificationAndOption specificationAndOption);

    // 查询实体
    SpecificationAndOption findOne(Long id);

    // 更新规格及规格选项
    boolean updateGroup(SpecificationAndOption specificationAndOption);

    // 删除
    boolean deleteGroup(Integer id);

    // 批量删除
    boolean deleteGroupBatch(List<Integer> asList);
}
