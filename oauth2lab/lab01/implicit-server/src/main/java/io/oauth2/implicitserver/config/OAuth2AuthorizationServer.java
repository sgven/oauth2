package io.oauth2.implicitserver.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter{

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 实验中图省事，所以用inMemory（客户端信息存在内存中），生产环境中一般用数据库jdbc的方式
        clients.inMemory()
                .withClient("clientapp")
                .secret("12345")
                .redirectUris("http://localhost:9001/callback")
                // 简化模式
                .authorizedGrantTypes("implicit")
                .accessTokenValiditySeconds(120)
                .scopes("read_userinfo","read_contacts");
    }
}
