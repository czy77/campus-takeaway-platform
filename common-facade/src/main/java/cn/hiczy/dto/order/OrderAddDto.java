package cn.hiczy.dto.order;

import cn.hiczy.entity.product.Food;
import lombok.Data;

/**
 * 订单传输对象
 * @author saco
 */
@Data
public class OrderAddDto {
    /**所购买的食物*/
    private Long foodId;

    /**食物购买数量*/
    private Integer count;

}
