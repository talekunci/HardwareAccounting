package com.accounting.HardwareAccounting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Profile("production")
@Configuration
@EnableWebSecurity
public class ProdWebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests().and()
                .formLogin().loginPage("login").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/register").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .cors()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated();

        return http.build();
    }

}
