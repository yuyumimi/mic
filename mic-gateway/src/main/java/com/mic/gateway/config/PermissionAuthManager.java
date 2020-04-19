package com.mic.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
/*
 * url权限认证
 *
 * @author zlt
 * @date 2019/10/6
 * <p>
 * Blog: https://blog.csdn.net/zlt2000
 * Github: https://github.com/zlt2000
 */

@Slf4j
@Component
public class PermissionAuthManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            return new AuthorizationDecision(true);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }
}
