package cn.hiczy.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author saco
 */
@SpringBootApplication
@EnableDubbo
@MapperScan("cn.hiczy.product.dao")
public class ProductServer {
    public static void main(String[] args) {
        SpringApplication.run(ProductServer.class, args);
    }
}
