package cn.hiczy.entity.order;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author saco
 */
@Data
@Accessors(chain = true)
public class OrderItem {
    /**
     * 订单详情项id
     */
    @TableId
    private String id;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * '食物id'
     */
    private Long foodId;
    /**
     * '商品购买数量'
     */
    private int num;
    /**
     * 食物名称
     */
    private String foodName;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 商品总金额
     */
    private BigDecimal totalFee;
    /**
     * '商品图片地址'
     */
    private String picPath;

    /**
     * 商铺id 不存入如数据，仅用来分类
     */
    private Long rId;
}
