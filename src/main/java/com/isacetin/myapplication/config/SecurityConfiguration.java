package com.isacetin.myapplication.config;

import com.isacetin.myapplication.security.jwt.JWTFilter;
import com.isacetin.myapplication.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;

    public SecurityConfiguration(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JWTFilter jwtFilter = new JWTFilter(tokenProvider);

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers("/api/authenticate")
                    .permitAll()
                    .requestMatchers("/api/register")
                    .permitAll()
                    .requestMatchers("/api/activate")
                    .permitAll()
                    .requestMatchers("/api/account/reset-password/init")
                    .permitAll()
                    .requestMatchers("/api/account/reset-password/finish")
                    .permitAll()
                    .requestMatchers("/api/admin/**")
                    .hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/api/**")
                    .authenticated()
                    .requestMatchers("/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui.html")
                    .permitAll()
                    .requestMatchers("/webjars/**")
                    .permitAll()
                    .requestMatchers("/management/health")
                    .permitAll()
                    .requestMatchers("/management/health/**")
                    .permitAll()
                    .requestMatchers("/management/info")
                    .permitAll()
                    .requestMatchers("/management/prometheus")
                    .permitAll()
                    .requestMatchers("/management/**")
                    .hasAuthority("ROLE_ADMIN")
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
