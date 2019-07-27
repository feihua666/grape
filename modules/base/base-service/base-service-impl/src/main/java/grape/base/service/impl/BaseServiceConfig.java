package grape.base.service.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 将配置单独提出来，以实现被依赖时自动配置
 * Created by yangwei
 * Created at 2019/7/25 9:04
 */
@Configuration()
@ComponentScan
@MapperScan("grape.base.service.impl.mapper")
public class BaseServiceConfig {
}
