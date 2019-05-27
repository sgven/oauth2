package io.oauth2.loginwithgithub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * 注册OAuth2客户端
 */
@Configuration
public class OAuth2LoginConfig {

    @EnableWebSecurity
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login()      // (1) 使用OAuth2认证
                    .and()
                    .logout()           // (2) 支持logout
                    .and()
                    .csrf().disable();  // (3) 支持通过GET方法进行logout
        }
    }

    /**
     * ClientRegistrationRepository充当ClientRegistration的存储库
     * (ClientRegistration)客户端注册信息最终由关联的授权服务器存储和拥有
     * ClientRegistrationRepository默认实现是InMemoryClientRegistrationRepository
     * The auto-configuration also registers the ClientRegistrationRepository as a @Bean in the ApplicationContext
     * @return
     */
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(aaaClientRegistration());
    }

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private String clientSecret;

    private ClientRegistration aaaClientRegistration() {
        return ClientRegistration.withRegistrationId("github")  // (1)
                .clientId(clientId)  // (2)
                .clientSecret(clientSecret)  // (3)
                .clientAuthenticationMethod(ClientAuthenticationMethod.POST)  // (4)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")  // (5)
                .clientName("github登录")       // (6)
                .tokenUri("http://localhost:8080/oauth/token")  // (7)
                .authorizationUri("http://localhost:8080/oauth/authorize")  // (8)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)  // (9)
                .scope("api")  // (10)
                .userNameAttributeName("username")  // (11)
                .userInfoUri("http://localhost:8080/api/v3/user")  // (12)
                .jwkSetUri("")  // (13)
                .build();
    }
}
