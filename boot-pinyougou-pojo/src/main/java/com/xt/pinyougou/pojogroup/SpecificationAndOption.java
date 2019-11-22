package com.xt.pinyougou.pojogroup;

import com.xt.pinyougou.pojo.Specification;
import com.xt.pinyougou.pojo.SpecificationOption;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 规格及规格选项
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SpecificationAndOption implements Serializable {

    private Specification specification;
    private List<SpecificationOption> specificationOptions;
}
