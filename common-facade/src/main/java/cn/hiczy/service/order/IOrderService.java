package cn.hiczy.service.order;

import cn.hiczy.entity.order.Order;
import cn.hiczy.entity.order.OrderItem;
import cn.hiczy.entity.product.Food;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单服务接口
 * @author saco
 */
public interface IOrderService extends IService<Order> {

    /**
     * 查询某个用户的所有订单
     * @param userId
     * @return
     */
    List<Order> selectListByUserId(Long userId);


    /**
     * 查询 某个商铺下的所有订单
     * @param rId
     * @return
     */
    List<Order> selectListByRid(Long rId);


    /**
     * 插入一条Order记录和多条OrderItem记录
     * @param userId
     * @param restaurantId
     * @param foodList
     */
    void insertOrder(Long userId, Long restaurantId, List<OrderItem> foodList);

}
