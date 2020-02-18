package com.heshaowei.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @author ChengJianSheng
 * @date 2019-02-11
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        security.tokenKeyAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//        clients.inMemory()
//                .withClient("lucky")
//                .secret("{bcrypt}$2a$10$RlOibqr.CEzIbV04fjg0Yea1ZoZAYxVVmUYDs5S.lTmAKgyVHUbdG")
//                .authorities("123456")
//                //有效时间 2小时
//                .accessTokenValiditySeconds(72000)
//                //密码授权模式和刷新令牌
//                .authorizedGrantTypes(new String[]{"authorization_code", "client_credentials", "refresh_token", "password"})
////                .resourceIds("myResourceServer")
//                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        endpoints.tokenStore(jwtTokenStore());
//        endpoints.tokenServices(defaultTokenServices());
    }

    /*@Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }*/

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("hshw");   //  Sets the JWT signing key
        return jwtAccessTokenConverter;
    }

}