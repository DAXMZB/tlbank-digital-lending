package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 【修改】先關閉 CSRF，避免前後端測試被擋
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/login.html",
                    "/register.html",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/api/members/register",
                    "/api/members/login",
                    "/api/members/check",
                    "/api/members/send-code",
                    "/api/members/forgot-password",
                    "/api/members/info",
                    "/api/products/**",
                    "/api/orders/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().permitAll() // 【修改】anyRequest 一定放最後，而且只能出現一次
            )
            .formLogin(form -> form.disable())   // 【修改】停用預設登入頁
            .httpBasic(httpBasic -> httpBasic.disable()); // 【修改】停用瀏覽器原生登入視窗

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}