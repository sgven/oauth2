//package io.oauth2.loginwithgithub.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 注册认证方式
// */
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()      // (1) 使用OAuth2认证
//                .and()
//                .logout()           // (2) 支持logout
//                .and()
//                .csrf().disable();  // (3) 支持通过GET方法进行logout
//    }
//}
