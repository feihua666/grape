package grape.workflow.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Created by yangwei
 * Created at 2019/7/24 19:39
 */
@SpringBootApplication(
        // 排除，主要是为了集成flowable 各种 ui 不再验证
        exclude = {SecurityAutoConfiguration.class}
)
public class FlowWorkRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowWorkRestApplication.class,args);
    }
}
