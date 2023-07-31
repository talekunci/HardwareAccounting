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
                .authorizeHttpRequests()
                .requestMatchers(
                        "/login",
                        "/register",
                        "/images/**",
                        "/css/**",
                        "/js/**"

                ).permitAll()
                .requestMatchers(
                        "/h2/**"
                ).denyAll();

        http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.usernameParameter("login");
                    login.passwordParameter("password");
                })
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic();

        http
                .csrf().disable();

        return http.build();
    }

}
