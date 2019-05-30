package io.oauth.authcodeapp.client;

import io.oauth.authcodeapp.client.interceptor.OAuth2ClientAuthenticationInterceptor;
import io.oauth.authcodeapp.client.oauth2.OAuth2API;
import io.oauth.authcodeapp.client.userinfo.UserInfoAPI;

public class ClientAPI {
    public static final String BASE_URL = "192.168.1.104:8080";

    public static UserInfoAPI userInfo() {
        RetrofitAPIFactory api = new RetrofitAPIFactory(BASE_URL, null);
        return api.getRetrofit().create(UserInfoAPI.class);
    }

    public static OAuth2API oauth2() {
        RetrofitAPIFactory api = new RetrofitAPIFactory(BASE_URL,
            new OAuth2ClientAuthenticationInterceptor());
        return api.getRetrofit().create(OAuth2API.class);
    }

}
