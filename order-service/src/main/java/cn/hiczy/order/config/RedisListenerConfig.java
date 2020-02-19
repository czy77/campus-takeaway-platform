package cn.hiczy.order.config;

import cn.hiczy.order.support.RedisMessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author saco
 */
@Configuration
public class RedisListenerConfig {

    @Autowired
    RedisMessageReceiver redisMessageReceiver;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListeners(listenerAdapter(redisMessageReceiver),
                new PatternTopic("__keyevent@0__:expired"));
        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageReceiver redisMessageReceiver) {
        return new MessageListenerAdapter(redisMessageReceiver,"receiveMessage")
    }

}
