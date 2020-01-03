package grape.base.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangwei
 * Created at 2019/7/24 19:39
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BaseRestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BaseRestApplication.class,args);
    }
}
