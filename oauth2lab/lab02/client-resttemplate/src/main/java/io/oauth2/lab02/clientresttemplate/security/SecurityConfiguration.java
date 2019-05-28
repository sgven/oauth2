package io.oauth2.lab02.clientresttemplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//        //inMemoryAuthentication 从内存中获取用户名和密码
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");

        // 在数据库中存储用户名和密码
        /** 这里升级了spring-boot版本，从1.5.10.RELEASE 升级到 2.1.5.RELEASE
         * <spring-security.version> 也从4.2.4.RELEASE 升级到 5.1.5.RELEASE
        */
        // 老版本升级后，会报错There is no PasswordEncoder mapped for the id "null"
        // auth.userDetailsService(userDetailsService);

        // Spring security 5.0 新增了多种加密方式，也改变了密码的格式，官方文档推荐使用BCrypt加密
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        // 仍然会报错Encoded password does not look like BCrypt，这意思是数据库的密码不像BCrypt加密后的
        // 1.需要修改数据库password字段长度，至少是varchar(60)
        // 2.将手动插入数据库的记录的原始密码，使用同样的加密方式加密后，保存到数据库
        //如password:$2a$10$KlzSHIdsd0q2jgPrsf2UTeluz./CWanknwX0qXFwSHJmX2DUvoGGG
//        System.out.println("password:"+(new BCryptPasswordEncoder().encode("xyz")));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers("/", "/index.html").permitAll().anyRequest().authenticated().and()
            .formLogin().and()
            .logout().permitAll().and()
            .csrf().disable();
    }

}
