package com.mic.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * http://localhost:50000/oauth/authorize?client_id=yuyu&redirect_uri=http://baidu.com&response_type=code&state=abc
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService myUserDetailService;
    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer tokenEnhancer;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;
    /**
     * 自定义OAuth2异常处理
     *
     * @return CustomWebResponseExceptionTranslator
     */
    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> customExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator();
    }

    /**
     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        if (jwtAccessTokenConverter != null) {
            if (tokenEnhancer != null) {
                TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
                tokenEnhancerChain.setTokenEnhancers(
                        Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));
                endpoints.tokenEnhancer(tokenEnhancerChain);
            } else {
                endpoints.accessTokenConverter(jwtAccessTokenConverter);
            }
        }
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
//                .redirectResolver(new MyDefaultRedirectResolver())
//                .userDetailsService(myUserDetailService)
//                .authorizationCodeServices(authorizationCodeServices)
//                .exceptionTranslator(webResponseExceptionTranslator)
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端信息，从数据库中读取，对应oauth_client_details表
        clients.jdbc(dataSource);

//        clients.inMemory().withClient("web-vue")
//                .secret(passwordEncoder.encode("123456"))
//                .scopes("r","w")
//                .accessTokenValiditySeconds(120)
//                .resourceIds("all").authorizedGrantTypes("password");

    }
    /**
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //permitAll()允许
        security
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()")
                //让/oauth/token支持client_id以及client_secret作登录认证
//                .allowFormAuthenticationForClients()
        ;
    }
}
