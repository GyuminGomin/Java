package com.edu.board.config;

import org.apache.tomcat.util.net.DispatchType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().disable()
            .authorizeRequests(request -> request
                .dispatcherTypeMatchers(DispatchType.FORWARD).permitAll()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .defaultSuccessUrl("/success", true)
            .permitAll()
        );

        return http.build();
    }
}
