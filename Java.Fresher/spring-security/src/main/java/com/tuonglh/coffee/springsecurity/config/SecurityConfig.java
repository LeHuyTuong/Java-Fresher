package com.tuonglh.coffee.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // active spring web security
@EnableGlobalMethodSecurity(jsr250Enabled = true )
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Tach backend va frontend
        http.formLogin((formLogin) -> formLogin.loginProcessingUrl("/login"));

        http.authorizeHttpRequests(req -> req
                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register")
                .permitAll()
                .requestMatchers("/v1/admin/normal").hasAnyRole("ADMIN","USER")
                .requestMatchers("/v1/admin/vip").hasRole("ADMIN")
                .anyRequest().authenticated()
        );




        return http.build();
    }

    /**
     * Config user information
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin123") // raw
//                .roles("user")      // gán vai trò  ROLE_admin tự gán thêm ROLE_ trước quyền
//                .authorities("admin") // là quyền bao gồm cả roles
//                .build();
//
//        UserDetails user = User
//                .withUsername("user")
//                .password("{noop}222")  //không mã hóa  // raw
//                .roles("user")
//                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("123"))
                //.roles("admin","user") //ROLE_ADMIN, ROLE_USERa
                .authorities("ROLE_admin", "ROLE_user", "order:read" , "order:write") // ROLE_USER, admin cx đc ,
                // ko nên dùng cùng lúc , sẽ bị ghi đè lên . dùng autho ngon hơn
                .build();
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(user,admin); // Cung cấp cho ta được lưu trong bộ nhớ sử dụng
        // spring security
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
