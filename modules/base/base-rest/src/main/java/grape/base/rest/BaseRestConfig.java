package grape.base.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 将配置单独提出来，以实现被依赖时自动配置
 * Created by yangwei
 * Created at 2019/7/25 9:04
 */
@Configuration
@ComponentScan
//@EnableSwagger2
public class BaseRestConfig {
}
