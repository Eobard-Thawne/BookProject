package com.book.config;

import com.alibaba.fastjson.JSON;
import com.book.entity.RestBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .formLogin((form) -> form.loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onSuccess))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    private void onSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(RestBean.success("登录成功")));
    }
}
