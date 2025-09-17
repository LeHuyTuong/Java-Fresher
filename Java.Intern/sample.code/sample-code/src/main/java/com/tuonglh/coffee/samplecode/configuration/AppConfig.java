package com.tuonglh.coffee.samplecode.configuration;


import com.tuonglh.coffee.samplecode.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppConfig  // implements WebMvcConfigurer // sử dụng được cho 1 mình WebMvcConfigurer
{

    private final UserService userService;
    private final PreFilter preFilter;
    private String[] WHITE_LIST = {"/auth/**"} ; // danh sách trắng cho phép truy cập những cái ko cần token

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        filterChain.doFilter(request,response);
//
//    } // request đến thì lọc đầu tiên


    @Bean
    public WebMvcConfigurer corsConfigurer() // sử dụng được cho 2 mình WebMvcConfigurer và OncePerRequestFilter
    {
        return new WebMvcConfigurer() { // cấu hình CORS
            @Override
            public void addCorsMappings( @NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("POST", "GET", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true)
                        .maxAge(3600);
            };
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { // mã hóa mật khẩu
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(@NotNull HttpSecurity http) throws Exception  // cấu hình bảo mật
    {
        http.cors(cors -> cors.configure(http))
            .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(WHITE_LIST)   // cho phép whitelist truy cập , là nơi thiết lập whitelist và blacklist
                                .permitAll()// không cần xác thực
                                .anyRequest() // còn lại
                                .authenticated())// phải xác thực
                                .sessionManagement(manager ->
                        manager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))// không lưu session giống http stateless , mỗi lần response là mất
                                .authenticationProvider(provider()).addFilterBefore(preFilter, UsernamePasswordAuthenticationFilter.class); // thêm filter tự tạo vào trước filter của spring security // lọc trước khi vào spring security
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){ // cấu hình để bỏ qua xác thực // thiết lập các url không cần bảo mật
        return webSecurity ->
                webSecurity.ignoring() // bỏ qua xác thực
                        .requestMatchers( "/actuator/**", "/v3/**", "/webjars/**"  ,"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"); // cho phép truy cập swagger mà không cần xác thực
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        // cung cấp authenticationManager để cấu hình trong SecurityConfig
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider provider(){ // truy cập DAO thông qua Service Detail
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // cung cấp xác thực dựa trên database
        provider.setUserDetailsService(userService.userDetailsService()); // lấy thông tin user từ database
        provider.setPasswordEncoder(getPasswordEncoder()); // mã hóa mật khẩu

        return provider;
    }




//    C1 @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*"); // accept language
//    }
//
//    C2 @Bean
//    public WebMvcConfigurer corsConfigurer()
//    {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("*")
//                        .allowCredentials(true);
//            };
//        };
//    }
//     C3   @Bean
//        public FilterRegistrationBean<CorsFilter> corsFilter(){ // lấy của spring boot
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowCredentials(true);
//            //config.addAllowedOrigin("http://localhost:3000");// 1 domain
//
//            config.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:3000", "gooole.com"));
//            config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
//            config.setAllowedHeaders(List.of("*"));
//            source.registerCorsConfiguration("/user/**", config);
//            FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//            bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//            return bean;
//        }

}
