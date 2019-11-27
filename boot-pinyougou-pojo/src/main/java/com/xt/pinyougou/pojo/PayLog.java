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
@TableName("tb_pay_log")
public class PayLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 支付订单号
     */
    @TableId(value = "out_trade_no", type = IdType.AUTO)
    private String outTradeNo;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 支付完成时间
     */
    private LocalDateTime payTime;

    /**
     * 支付金额（分）
     */
    private Long totalFee;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 交易号码
     */
    private String transactionId;

    /**
     * 交易状态
     */
    private String tradeState;

    /**
     * 订单编号列表
     */
    private String orderList;

    /**
     * 支付类型
     */
    private String payType;


}
