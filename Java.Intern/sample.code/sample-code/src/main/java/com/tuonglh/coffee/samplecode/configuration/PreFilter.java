package com.tuonglh.coffee.samplecode.configuration;


import com.tuonglh.coffee.samplecode.service.JWTService;
import com.tuonglh.coffee.samplecode.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class PreFilter  extends OncePerRequestFilter {

    private final UserService userService;
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(" ----------------PreFilter--------------");

        final String authorization = request.getHeader("Authorization");
        log.info("Authorization : {} ", authorization );

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorization.substring("Bearer ".length());
        log.info("Token : {} ", token );

        try{
        final String userName = jwtService.extractUserName(token);

        if(SecurityContextHolder.getContext().getAuthentication() == null && userName == null){
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userName); // check DB xem user co ton tai trong DB khong
            if(jwtService.isTokenValid(token,userDetails)) { // check xem co hop le ko
                SecurityContext context = SecurityContextHolder.createEmptyContext(); // tao cai moi

                // tao moi voi thong tin user
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // truyen them vao request
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //context gan authen
                context.setAuthentication(authentication);

                // gan lai cho contextHolder
                SecurityContextHolder.setContext(context);
            }
        }
        } catch (Exception e) {
            // Chỉ log lỗi để biết, không throw exception để tránh lỗi 500
            log.error("Token validation error: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
