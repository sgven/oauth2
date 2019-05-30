package io.oauth.authcodeapp.client;

import io.oauth.authcodeapp.client.interceptor.BearerTokenHeaderInterceptor;
import io.oauth.authcodeapp.client.interceptor.ErrorInterceptor;
import io.oauth.authcodeapp.client.interceptor.OAuth2ClientAuthenticationInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

class RetrofitAPIFactory {
    private final Retrofit retrofit;

    RetrofitAPIFactory(String baseUrl,
       OAuth2ClientAuthenticationInterceptor clientAuthentication) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://" + baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(createClient(clientAuthentication))
                .build();
    }

    public Retrofit getRetrofit() { return retrofit; }

    private OkHttpClient createClient(
        OAuth2ClientAuthenticationInterceptor clientAuthentication) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new ErrorInterceptor());
        client.addInterceptor(new BearerTokenHeaderInterceptor());
        if (clientAuthentication != null) {
            client.addInterceptor(clientAuthentication);
        }
        return client.build();
    }
}
