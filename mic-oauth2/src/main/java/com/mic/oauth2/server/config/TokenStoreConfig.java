package com.mic.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class TokenStoreConfig {
    @Configuration
//    @ConditionalOnProperty(prefix = "mic.oauth2.token.store", name =
//            "type", havingValue = "jdbc")
    public class JdbcTokenConfig {
        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }
    }

//    @Configuration
//    @ConditionalOnProperty(prefix = "mic.oauth2.token.store", name =
//            "type", havingValue = "redis", matchIfMissing = true)
//    public class RedisTokenConfig {
//        @Autowired
//        private RedisConnectionFactory redisConnectionFactory;
//
//        @Bean
//        public TokenStore tokenStore() {
//            // 基于 JDBC 实现，令牌保存到数据库
//            return new RedisTokenStore(redisConnectionFactory);
//        }
//    }


//    @Configuration
//    @ConditionalOnProperty(prefix = "mic.oauth2.token.store",
//            name = "type", havingValue = "resSignJwt")
//    public class AuthJwtTokenConfig {
//        @Autowired
//        private CustomizerSecurityProperties securityProperties;
//
//        @Bean
//        public TokenStore tokenStore() {
//            return new JwtTokenStore(accessTokenConverter());
//        }
//
//        @Bean
//        public JwtAccessTokenConverter accessTokenConverter() {
//            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//            converter.setSigningKey(securityProperties.getJwt().getSigningKey());
//            return converter;
//        }
//    }
//
//    @Configuration
//    @ConditionalOnProperty(prefix = "mic.oauth2.token.store",
//            name = "type", havingValue = "resJwt")
//    public class ResJwtTokenConfig {
//        @Autowired
//        private ResourceServerProperties resource;
//
//        @Bean
//        public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
//            return new JwtTokenStore(jwtAccessTokenConverter);
//        }
//
//        @Bean
//        public JwtAccessTokenConverter jwtAccessTokenConverter() {
//            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//            converter.setVerifierKey(getPubKey());
//            DefaultAccessTokenConverter tokenConverter =
//                    (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
//            tokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
//            return converter;
//        }
//
//        /**
//         * 获取非对称加密公钥 Key
//         *
//         * @return 公钥 Key
//         */
//        private String getPubKey() {
//            Resource res =
//                    new ClassPathResource(SecurityConstants.RSA_PUBLIC_KEY);
//            try (BufferedReader br =
//                         new BufferedReader(new InputStreamReader(res.getInputStream()))) {
//                return br.lines().collect(Collectors.joining("\n"));
//            } catch (IOException ioe) {
//                return getKeyFromAuthorizationServer();
//            }
//        }
//
//        /**
//         * 通过访问授权服务器获取非对称加密公钥 Key
//         *
//         * @return 公钥 Key
//         */
//        private String getKeyFromAuthorizationServer() {
//            if (Strings.isNotEmpty(this.resource.getJwt().getKeyUri())) {
//                final HttpHeaders headers = new HttpHeaders();
//                final String username = this.resource.getClientId();
//                final String password = this.resource.getClientSecret();
//                if (username != null && password != null) {
//                    final byte[] token =
//                            Base64.getEncoder().encode((username + ":" + password).getBytes());
//                    headers.add("Authorization", "Basic " + new String(token));
//                }
//                final HttpEntity<Void> request = new HttpEntity<>(headers);
//                final String url = this.resource.getJwt().getKeyUri();
//                return (String) new RestTemplate()
//                        .exchange(url, HttpMethod.GET, request, Map.class).getBody()
//                        .get("value");
//            }
//            return null;
//        }
//    }
}