package grape.auth.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by yangwei
 * Created at 2019/7/24 19:39
 */
@EnableDiscoveryClient
@SpringBootApplication(
/*        exclude = {
                ErrorMvcAutoConfiguration.class
        }*/
)
public class AuthRestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AuthRestApplication.class,args);
    }
}
