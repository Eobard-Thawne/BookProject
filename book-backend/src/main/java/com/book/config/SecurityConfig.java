package com.book.config;

import com.alibaba.fastjson.JSON;
import com.book.entity.RestBean;
import com.book.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    AuthService authService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PersistentTokenRepository repository) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .formLogin((form) -> form.loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onSuccess)
                        .failureHandler(this::onFailure)
                )
                .logout((logout) -> logout.logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onSuccess))
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(authService)
                .cors((cors) -> cors.configurationSource(this.configurationSource()))
                .rememberMe((rmb) -> rmb.rememberMeParameter("remember_me")
                        .tokenRepository(repository)
                        .tokenValiditySeconds(3600 * 24 * 7))
                .build();
    }

    @Bean
    PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
//        repository.setCreateTableOnStartup(true);
        repository.setDataSource(dataSource);
        return repository;
    }

    // 跨域设置
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return configurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void onFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(RestBean.failure(401, e.getMessage())));
    }

    private void onSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
        if (request.getRequestURL().toString().endsWith("/logout")) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(RestBean.success("退出登录成功")));
        } else if (request.getRequestURL().toString().endsWith("/login")) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(RestBean.success("登录成功")));
        }
    }
}
