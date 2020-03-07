package grape.base.rest.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 */
@Configuration
@EnableFeignClients
@ComponentScan
public class BaseRestClientConfig {
}
