package com.xt.pinyougou.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xt
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_goods_desc")
public class GoodsDesc implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * SPU_ID
     */
    @TableId(value = "goods_id")
    private Long goodsId;

    /**
     * 描述
     */
    private String introduction;

    /**
     * 规格结果集，所有规格，包含isSelected
     */
    private String specificationItems;

    /**
     * 自定义属性（参数结果）
     */
    private String customAttributeItems;

    private String itemImages;

    /**
     * 包装列表
     */
    private String packageList;

    /**
     * 售后服务
     */
    private String saleService;


}
