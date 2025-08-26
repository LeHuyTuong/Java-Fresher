package com.tuonglh.coffee.samplecode.configuration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Component
public class AppConfig extends OncePerRequestFilter // implements WebMvcConfigurer // sử dụng được cho 1 mình WebMvcConfigurer
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        filterChain.doFilter(request,response);

    } // request đến thì lọc đầu tiên
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
