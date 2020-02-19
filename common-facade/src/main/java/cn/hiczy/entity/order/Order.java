package cn.hiczy.entity.order;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.management.LockInfo;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author saco
 */
@Data
@Accessors(chain = true)
public class Order {

    /**
     * 订单id
     */
    @TableId
    private String orderId;
    /**
     * 实付金额 精确到2位小数
     */
    private BigDecimal payment;
    /**
     * 状态：1、未付款，2、已付款，3、交易成功，4、交易关闭
     */
    private Integer status;
    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;
    /**
     * 订单更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 付款时间
     */
    private LocalDateTime paymentTime;
     /**
     * 交易完成
     */
    private LocalDateTime endTime;
    /**
     * 交易关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * '用户id'
     */
    private Long userId;

    /**
     * 商铺ID
     */
    private Long rId;
}
