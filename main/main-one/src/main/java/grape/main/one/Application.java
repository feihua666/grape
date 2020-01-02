package grape.main.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 单机聚合运行入口
 * Created by yangwei
 * Created at 2019/7/27 15:24
 */
@SpringBootApplication(
        // 排除，主要是为了集成flowable 各种 ui 不再验证
        exclude = {SecurityAutoConfiguration.class}
)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
