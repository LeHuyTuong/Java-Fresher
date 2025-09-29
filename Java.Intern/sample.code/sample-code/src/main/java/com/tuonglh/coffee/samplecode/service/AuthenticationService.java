package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.dto.request.SignInRequest;
import com.tuonglh.coffee.samplecode.dto.response.TokenResponse;
import com.tuonglh.coffee.samplecode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    /**
     * Xác thực người dùng dựa trên thông tin đăng nhập nhận được.
     *
     * 1. Sử dụng AuthenticationManager để xác thực username và password.
     * 2. Tìm kiếm người dùng trong database theo username.
     *    - Nếu không tìm thấy sẽ ném ra UsernameNotFoundException.
     * 3. Trả về TokenResponse gồm:
     *    - accessToken (giá trị mẫu)
     *    - refreshToken (giá trị mẫu)
     *    - userId của người dùng đã xác thực
     */
    public TokenResponse authenticate(SignInRequest signInRequest) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword()));
        var user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username hoặc mật khẩu không đúng"));

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateToken(user);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken( refreshToken)
                .userId(user.getId())
                .build();
    }
}
