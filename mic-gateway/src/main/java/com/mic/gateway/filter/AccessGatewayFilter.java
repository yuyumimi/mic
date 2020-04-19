package com.mic.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mic.common.dto.CommonResult;
import com.mic.common.exception.GatewayErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Configuration
@Slf4j
@Order(-1000)
public class AccessGatewayFilter implements GlobalFilter,Order {

    private final static String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private final static String X_CLIENT_TOKEN = "x-client-token";
    private static final String BEARER = "bearer";

    private AntPathMatcher matcher = new AntPathMatcher();
    @Value("${gateway.ignore.authentication.uri}")
    private String[] ignoreUrls;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{}", url, method,
                request.getHeaders());
//        //不需要网关签权的url
//        if (ignoreUrl(url, ignoreUrls)) {
//            return chain.filter(exchange);
//        }
//        String authentication =
//                request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        // 如果请求未携带token信息, 直接跳出
//        if (StringUtils.isBlank(authentication) || !authentication.startsWith(BEARER)) {
//            log.debug("url:{},method:{},headers:{}, 请求未携带token信息",
//                    url, method, request.getHeaders());
//            return unauthorized(exchange);
//        }

        return chain.filter(exchange);
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) throws JsonProcessingException {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        CommonResult result =
                CommonResult.builder().build()
                        .failed(GatewayErrorType.GATEWAY_UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
        serverWebExchange.getResponse().getHeaders()
                .add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    private boolean ignoreUrl(String uri, String... patterns) {
        int size = patterns.length;
        for (int i = 0; i < size; i++) {
            if (matcher.match(patterns[i], uri)) {
                return true;
            }
            ;
        }
        return false;
    }

    @Override
    public int value() {
        return -1000;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
