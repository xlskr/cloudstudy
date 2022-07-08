package com.xulei.cn.filter;


import com.xulei.cn.fegin.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private SystemService systemService;

    @Value("${const.nointercetorpath}")
    private String path;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String reqPath = exchange.getRequest().getURI().getPath();
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(reqPath.matches(path)){
            return chain.filter(exchange);
        }

        if(systemService.isPermitted(reqPath,token)){
            ServerHttpResponse httpResponse = exchange.getResponse();
            //修改code为500
            httpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            if (!httpResponse.getHeaders().containsKey("Content-Type")) {
                httpResponse.getHeaders().add("Content-Type", "application/json");
            }
            //此处无法触发全局异常处理，手动返回
            DataBuffer buffer = httpResponse.bufferFactory().wrap(("{\n"
                    + "  \"code\": 100,"
                    + "  \"message\": \"权限不足\","
                    + "  \"data\": \"Server throttling\","
                    + "  \"success\": false"
                    + "}").getBytes(StandardCharsets.UTF_8));
            return httpResponse.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);

    }

    /**
     * 过滤器的优先级，越低越高
     */
    @Override
    public int getOrder() {
        return 1;
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        return null;
//    }
}