package com.bitstudy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin().and().build();

/*
        http.formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/index")
                .failureUrl("/login.html?error")
                .usernameParameter("유저이름")
*/

    }
}
