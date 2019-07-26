package grape.base.service.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangwei
 * Created at 2019/7/24 18:11
 */
@SpringBootApplication(scanBasePackages = "grape.base.service.impl.impl")
@MapperScan("grape.base.service.impl.mapper")
public class BaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
    }
}
