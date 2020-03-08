package grape.gateway.filters;

import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

/**
 * 全局过滤器，目前主要是生成requestId和记录耗时
 * Created by yangwei
 * Created at 2020/1/6 17:07
 */
@Slf4j
@Component
public class GlobalGatewayFilter implements GatewayFilter, Ordered, ToolService {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestId = RequestIdTool.initRequestId();

        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();

        // 获取命中的路由
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        log.info("请求开始,requestId=[{}],route=[{}]",requestId,route.toString());

        // 记录请求开始时间
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        exchange.getAttributes().put(RequestIdTool.reqeustIdKey, requestId);
        ServerHttpRequest.Builder builder = request.mutate();
        // 添加请求id头信息
        builder.header(RequestIdTool.reqeustIdKey, requestId);

        return chain.filter(exchange.mutate().request(builder.build()).build()).then(Mono.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                String requestId = exchange.getAttribute(RequestIdTool.reqeustIdKey);
                if (startTime != null && requestId != null) {
                    //打印
                    log.info("请求结束，requestId=[{}],uri=[{}],duration=[{}ms]",
                            requestId,
                            exchange.getRequest().getURI(),
                            (System.currentTimeMillis() - startTime));
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


    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
