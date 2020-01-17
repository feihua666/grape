package grape.gateway.filters.factory;

import grape.gateway.filters.GlobalGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * 全局过滤器工厂类
 * 名字默认是GlobalGatewayFilterFactory的类名并去掉了GatewayFilterFactory 参见实现：org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory#name()
 * Created by yangwei
 * Created at 2020/1/6 18:14
 */
@Component
public class GlobalGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Autowired
    private GlobalGatewayFilter globalGlobalFilter;
    @Override
    public GatewayFilter apply(Object config) {
        return globalGlobalFilter;
    }
}
