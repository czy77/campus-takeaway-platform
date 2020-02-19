package cn.hiczy.order.service;

import cn.hiczy.entity.order.Order;
import cn.hiczy.entity.order.OrderItem;
import cn.hiczy.entity.product.Category;
import cn.hiczy.entity.product.Food;
import cn.hiczy.order.dao.OrderItemMapper;
import cn.hiczy.order.dao.OrderMapper;
import cn.hiczy.service.order.IOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 订单服务实现类
 * @author saco
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public List<Order> selectListByUserId(Long userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        return orders;
    }

    @Override
    public List<Order> selectListByRid(Long rId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("r_id", rId);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        return orders;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOrder(Long userId, Long restaurantId, List<OrderItem> orderItemList) {
        BigDecimal sumPrice = new BigDecimal(0);
        String orderId = UUID.randomUUID().toString();
        for (OrderItem o : orderItemList) {
            o.setOrderId(orderId);
            sumPrice.add(o.getTotalFee());
        }
        Order order = new Order();
        order.setPayment(sumPrice)
                .setOrderId(orderId)
                .setRId(restaurantId)
                .setStatus(1);
        orderMapper.insert(order);

        for (OrderItem o : orderItemList) {
            orderItemMapper.insert(o);
        }
    }
}
