package com.mic.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mic.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 403拒绝访问异常处理，转换为JSON
 *
 * @author zlt
 * @date 2019/10/7
 * <p>
 * Blog: https://blog.csdn.net/zlt2000
 * Github: https://github.com/zlt2000
 */
@Slf4j
public class JsonAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        try {
            return ResponseUtil.responseWriter(exchange, HttpStatus.FORBIDDEN.value(), e.getMessage());
        } catch (JsonProcessingException ex) {
            log.error("",e);
        }
        return null;
    }
}