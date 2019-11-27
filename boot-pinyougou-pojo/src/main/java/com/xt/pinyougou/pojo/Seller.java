package com.xt.pinyougou.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_seller")
public class Seller implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "seller_id")
    private String sellerId;

    /**
     * 公司名
     */
    private String name;

    /**
     * 店铺名称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * EMAIL
     */
    private String email;

    /**
     * 公司手机
     */
    private String mobile;

    /**
     * 公司电话
     */
    private String telephone;

    /**
     * 状态
     */
    private String status;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 联系人姓名
     */
    private String linkmanName;

    /**
     * 联系人QQ
     */
    private String linkmanQq;

    /**
     * 联系人电话
     */
    private String linkmanMobile;

    /**
     * 联系人EMAIL
     */
    private String linkmanEmail;

    /**
     * 营业执照号
     */
    private String licenseNumber;

    /**
     * 税务登记证号
     */
    private String taxNumber;

    /**
     * 组织机构代码
     */
    private String orgNumber;

    /**
     * 公司地址
     */
    private Long address;

    /**
     * 公司LOGO图
     */
    private String logoPic;

    /**
     * 简介
     */
    private String brief;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 法定代表人
     */
    private String legalPerson;

    /**
     * 法定代表人身份证
     */
    private String legalPersonCardId;

    /**
     * 开户行账号名称
     */
    private String bankUser;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankCard;

}
