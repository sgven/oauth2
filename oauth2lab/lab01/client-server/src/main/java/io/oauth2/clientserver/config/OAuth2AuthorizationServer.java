package io.oauth2.clientserver.config;

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
                .withClient("clientdevops")
                .secret("123")
                // 客户端模式
                .authorizedGrantTypes("client_credentials")
                .scopes("devops");
    }
}
