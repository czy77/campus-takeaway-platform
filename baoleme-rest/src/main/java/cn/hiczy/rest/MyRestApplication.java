package cn.hiczy.rest;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author saco
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2
@EnableDubbo
@MapperScan("cn.hiczy.rest.security.dao")
public class MyRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyRestApplication.class, args);
    }

}
