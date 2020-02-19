package cn.hiczy.rest.controller.order;

import cn.hiczy.dto.order.OrderAddDto;
import cn.hiczy.entity.order.Order;
import cn.hiczy.entity.order.OrderItem;
import cn.hiczy.entity.product.Food;
import cn.hiczy.entity.product.Restaurant;
import cn.hiczy.rest.security.entity.User;
import cn.hiczy.service.order.IOrderService;
import cn.hiczy.service.product.IFoodService;
import cn.hiczy.vo.CommonResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.pubsub.RedisPubSubListener;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author saco
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Reference
    IOrderService orderServiceImpl;

    @Reference
    IFoodService foodServiceImpl;

    @PostMapping
    public CommonResult addOrder(List<OrderAddDto> addDtoList) throws IOException {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<OrderItem> orderItemList = new ArrayList<>();
//      //用户买了哪些
        List<Food> foodList = new ArrayList<>();
        //生成OrderItem
        for (OrderAddDto dto : addDtoList) {
            Long foodId = dto.getFoodId();
            Food food = foodServiceImpl.getById(foodId);
            BigDecimal sumPrice = food.getPrice().multiply(new BigDecimal(dto.getCount()));
            OrderItem orderItem = new OrderItem();

            orderItem.setFoodId(food.getId())
                    .setFoodName(food.getName())
                    .setId(UUID.randomUUID().toString())
                    .setNum(dto.getCount())
                    .setPicPath(food.getPicture())
                    .setRId(food.getRId())
                    .setTotalFee(sumPrice);
            orderItemList.add(orderItem);
            foodList.add(food);
        }

        Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream().collect(Collectors.groupingBy(OrderItem::getRId));
        orderItemMap.forEach((rid, orderItems)->{
            orderServiceImpl.insertOrder(principal.getId(), rid, orderItems);
        });

        return CommonResult.success("订单已创建");
    }


//    @GetMapping("{username}")
//    public CommonResult<List<Order> > getOrder(@PathVariable Long userId) throws JsonProcessingException {
//        List<Order> orders = orderServiceImpl.selectListByUserId(userId);
//        return CommonResult.success(orders);
//    }
//
//    @GetMapping("orderItems")
//    public CommonResult orderItems(@RequestParam("orderNum") String orderNum) {
//        List<OrderItems> itemsList = orderService.getOrderItemsListByOrderNum(orderNum);
//        return new CommonResult(ReturnCode.SUCCESS.getCode(), "OK", itemsList);
//    }
//
//    @GetMapping
//    public CommonResult allOrders(@RequestParam(value = "owner_id", required = false) String owner_id) {
//        Map data = new HashMap();
//        List<Restaurant> restaurants = new ArrayList<>();
//        List<Order> orderList = new ArrayList<>();
//        if (owner_id == null || "".equals(owner_id)) {
//            //获取餐厅的全部订单
//            orderList = orderService.getAllOrder();
//            data.put("orderList", orderList);
//            //通过订单中的rId获取对应的餐厅
//            orderList.forEach(order -> restaurants.add(restaurantService.getRstById(order.getrId())));
//            data.put("restaurants", restaurants);
//            return new CommonResult(ReturnCode.SUCCESS.getCode(), "获取数据成功!", data);
//        }
//        orderList = orderService.getRestaurantOrders(Integer.parseInt(owner_id));
//        data.put("orderList", orderList);
//        //通过订单中的rId获取对应的餐厅
//        orderList.forEach(order -> restaurants.add(restaurantService.getRstById(order.getrId())));
//        data.put("restaurants", restaurants);
//        return new CommonResult(ReturnCode.SUCCESS.getCode(), "获取数据成功!", data);
//    }

}
