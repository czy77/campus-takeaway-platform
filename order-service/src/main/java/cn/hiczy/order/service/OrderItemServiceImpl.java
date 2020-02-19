package cn.hiczy.order.service;

import cn.hiczy.entity.order.OrderItem;
import cn.hiczy.order.dao.OrderItemMapper;
import cn.hiczy.order.dao.OrderMapper;
import cn.hiczy.service.order.IOrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * OrderItem服务实现类
 * @author saco
 */
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> selectListByOrderId(String orderId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
        return orderItems;
    }



}
