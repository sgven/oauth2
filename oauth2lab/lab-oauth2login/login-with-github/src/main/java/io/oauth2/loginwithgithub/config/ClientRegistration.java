package io.oauth2.loginwithgithub.config;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;

public final class ClientRegistration {
    private String registrationId;//ClientRegistration唯一标识的ID
    private String clientId;//客户端标识符
    private String clientSecret;//客户端密钥
    private ClientAuthenticationMethod clientAuthenticationMethod;//用于使用Provider对客户端进行身份验证的方法,The supported values are basic and post
    private AuthorizationGrantType authorizationGrantType;//授权类型,The supported values are authorization_code and implicit.
    private String redirectUriTemplate;//客户端的注册重定向URI,它支持URI模板变量,默认重定向URI模板是{baseUrl}/login/oauth2/code/{registrationId}
    private Set<String> scopes;//客户端在授权请求流程中请求的范围
    private ProviderDetails providerDetails;
    private String clientName;//用于客户端的描述性名称

    public class ProviderDetails {
        private String authorizationUri;//授权服务器的授权端点URI
        private String tokenUri;//授权服务器的令牌端点URI
        private UserInfoEndpoint userInfoEndpoint;
        private String jwkSetUri;//从授权服务器检索JSON Web密钥（JWK）集的URI

        public class UserInfoEndpoint {
            private String uri;//UserInfo端点URI
            private String userNameAttributeName;//UserInfo响应中返回的属性的名称

        }
    }
}