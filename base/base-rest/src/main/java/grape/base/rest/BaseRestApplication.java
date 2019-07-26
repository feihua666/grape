package grape.base.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by yangwei
 * Created at 2019/7/24 19:39
 */
@SpringBootApplication
public class BaseRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseRestApplication.class,args);
    }
}
