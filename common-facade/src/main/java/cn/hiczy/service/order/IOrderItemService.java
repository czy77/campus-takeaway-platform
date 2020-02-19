package cn.hiczy.service.order;

import cn.hiczy.entity.order.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单详情服务接口
 * @author saco
 */
public interface IOrderItemService extends IService<OrderItem> {

    /**
     *  获取某订单下的所有商品
     * @param orderId
     * @return
     */
    List<OrderItem> selectListByOrderId(String orderId);





}
