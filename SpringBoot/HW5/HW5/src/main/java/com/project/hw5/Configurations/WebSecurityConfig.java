package com.project.hw5.Configurations;

import com.project.hw5.Filters.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final LoggingFilter loggingFilter;

    @Autowired
    public WebSecurityConfig(LoggingFilter loggingFilter) {
        this.loggingFilter = loggingFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class) // Add LoggingFilter before auth
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**","/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                ;

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
