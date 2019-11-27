package com.xt.pinyougou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置静态的资源允许所有访问
                .antMatchers("/webjars/**", "/static/**", "/login", "/", "/index.html").permitAll()
                // 其他所有资源都需要登陆后才能访问
                .anyRequest().authenticated();

        http.formLogin()
                // 设置默认登陆页面路径 /login
                .loginPage("/login")
                // 强制指定登陆成功后跳转的路径
                .defaultSuccessUrl("/admin/index")
        ;

        /**
         * IFrame 出 Refused to display 'URL' in a frame because it set 'X-Frame-Options' to 'DENY'
         */
        http.headers().frameOptions().sameOrigin();

        http.logout().logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("admin");
    }

}
