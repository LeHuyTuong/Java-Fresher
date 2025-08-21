package com.tuonglh.coffee.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // active spring web security
public class SecurityConfig {

    /**
     * Config user information
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123") // raw
                .roles("user")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password("{noop}222")  //không mã hóa  // raw
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(user,admin); // Cung cấp cho ta được lưu trong bộ nhớ sử dụng
        // spring security
    }

}
