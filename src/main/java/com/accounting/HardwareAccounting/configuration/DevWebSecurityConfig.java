package com.accounting.HardwareAccounting.configuration;

import com.accounting.HardwareAccounting.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class DevWebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public DevWebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/h2/**",
                        "/login",
                        "/register",
                        "/images/**",
                        "/css/**",
                        "/js/**"

                ).permitAll();

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
                .csrf().disable()
                .headers().frameOptions().disable();

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth
                .userDetailsService(userDetailsService);
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("123"))
                .roles("ROLE_User");
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("890"))
                .roles("ROLE_Admin");
    }

}
