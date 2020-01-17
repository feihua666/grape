package grape.gateway.filters;

import grape.common.tools.RequestIdTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

/**
 * 全局过滤器，目前主要是生成requestId和记录耗时
 * Created by yangwei
 * Created at 2020/1/6 17:07
 */
@Slf4j
@Component
public class GlobalGatewayFilter implements GatewayFilter, Ordered {
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestId = RequestIdTool.initRequestId();
        // 获取命中的路由
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        log.info("请求开始,requestId=[{}],route=[{}]",requestId,route.toString());
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header(RequestIdTool.reqeustIdKey, requestId).build();

        // 记录请求开始时间
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        exchange.getAttributes().put(RequestIdTool.reqeustIdKey, requestId);

        return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                String requestId = exchange.getAttribute(RequestIdTool.reqeustIdKey);
                if (startTime != null && requestId != null) {
                    //打印
                    log.info("请求结束，requestId=[{}],uri=[{}],duration=[{}ms]",requestId,exchange.getRequest().getURI(),(System.currentTimeMillis() - startTime));
                }else {
                    log.warn("请求结束，未获取到请求开始时间或requestId,uri=[{}]",exchange.getRequest().getURI());
                }

            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String toString() {
        return filterToStringCreator(GlobalGatewayFilter.this).toString();
    }
}
