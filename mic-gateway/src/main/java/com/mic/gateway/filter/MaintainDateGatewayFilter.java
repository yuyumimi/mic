package com.mic.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mic.common.dto.CommonResult;
import com.mic.common.exception.GatewayErrorType;
import com.mic.gateway.properties.RunDateDateProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.LocalDateTime;

/**
 * @ClassName MaintainDateGatewayFilter
 * @Description TODO 待开发接口级别拦截
 * @Author yuyu
 * @Date 2019/12/19 12:01
 * @Version 1.0
 **/
@Configuration
@Slf4j
@Order(-1001)
public class MaintainDateGatewayFilter implements GlobalFilter {

    private AntPathMatcher matcher = new AntPathMatcher();
    @Autowired
    private RunDateDateProperties dateProperties;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (this.dateProperties.isEnable()) {
            String requestURI = request.getPath().value();
            if (excludeRouces(requestURI,this.dateProperties.getIgnoreUri())) {
                return chain.filter(exchange);
            }
            if (isMaintainDate()) {
                try {
                    return maintain(exchange);
                } catch (JsonProcessingException e) {
                    log.error("",e);
                }
            }
        }
        return chain.filter(exchange);
    }

    /**
     * 检查是运维期吗
     *
     * @return
     */
    private boolean isMaintainDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        int dayOfMonth = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        if (isMaintainDay(dayOfMonth) || isMaintainHour(hour)) {
            return true;
        }
        return false;
    }

    private boolean isMaintainDay(int dayOfMonth) {
        if (dayOfMonth >= this.dateProperties.getStartDay()
                && dayOfMonth <= this.dateProperties.getEndDay()) {
            return false;
        }
        return true;
    }

    private boolean isMaintainHour(int hour) {
        if (hour >= this.dateProperties.getStartHour()
                && hour <= this.dateProperties.getEndHour()) {
            return false;
        }
        return true;
    }

    /**
     * 不拦截资源判断
     *
     * @param uri
     * @param patterns
     * @return
     */
    private boolean excludeRouces(String uri, String... patterns) {
        int size = patterns.length;
        for (int i = 0; i < size; i++) {
            if (matcher.match(patterns[i], uri)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> maintain(ServerWebExchange serverWebExchange) throws JsonProcessingException {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        CommonResult result =
                CommonResult.builder().build()
                        .failed(GatewayErrorType.GATEWAY_MAINTAIN);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
        serverWebExchange.getResponse().getHeaders()
                .add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
