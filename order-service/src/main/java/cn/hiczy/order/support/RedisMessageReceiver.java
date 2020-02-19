package cn.hiczy.order.support;

import org.springframework.stereotype.Component;

/**
 * @author saco
 */
@Component
public class RedisMessageReceiver {

    public void receiveMessage(String key) {
        //更新数据库订单状态
    }
}
