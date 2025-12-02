package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.dto.request.ResetPasswordDTO;
import com.tuonglh.coffee.samplecode.dto.request.SignInRequest;
import com.tuonglh.coffee.samplecode.dto.response.TokenResponse;
import com.tuonglh.coffee.samplecode.exception.InvalidDataException;
import com.tuonglh.coffee.samplecode.model.RedisToken;
import com.tuonglh.coffee.samplecode.model.Token;
import com.tuonglh.coffee.samplecode.model.User;
import com.tuonglh.coffee.samplecode.repository.UserRepository;
import com.tuonglh.coffee.samplecode.util.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.REFERER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final RedisTokenService redisTokenService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse accessToken(SignInRequest signInRequest) {
        log.info("---------authenticate----------------");

        var user = userService.getByUsername(signInRequest.getUsername());
        if (!user.isEnabled()) {
            throw new InvalidDataException("User is not active");
        }

        List<String> roles = userService.getAllRolesByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword(), authorities));

        //create new access token
        String accessToken = jwtService.generateToken(user);

        // create new refresh token
        String refreshToken = jwtService.generateRefreshToken(user);
//
//        tokenService.save(Token.builder()
//                .username(user.getUsername())
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build());

        // save token to redis kiem soat duoc viec token het han
        redisTokenService.save(RedisToken.builder()
                .id(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }

    /**
     * Xác thực người dùng dựa trên thông tin đăng nhập nhận được.
     * <p>
     * 1. Sử dụng AuthenticationManager để xác thực username và password.
     * 2. Tìm kiếm người dùng trong database theo username.
     * - Nếu không tìm thấy sẽ ném ra UsernameNotFoundException.
     * 3. Trả về TokenResponse gồm:
     * - accessToken (giá trị mẫu)
     * - refreshToken (giá trị mẫu)
     * - userId của người dùng đã xác thực
     */
    public TokenResponse authenticate(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        var user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username hoặc mật khẩu không đúng"));

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }


    public TokenResponse refresh(HttpServletRequest request) {
        // validate token
        String refreshToken = request.getHeader("x-token");
        if (StringUtils.isBlank(refreshToken)) {
            throw new InvalidDataException("token is empty");
        }

        // B1 extract user from token
        final String userName = jwtService.extractUserName(refreshToken, TokenType.REFRESH_TOKEN);
        System.out.println("userName: " + userName);

        // B2 check it into db
        var user = userRepository.findByUsername(userName);
        System.out.println("userId= " + user.get().getId());

        if (!jwtService.isTokenValid(refreshToken, user.get(), TokenType.REFRESH_TOKEN)) {
            throw new InvalidDataException("token is invalid");
        }

        String accessToken = jwtService.generateToken(user.get());
//        tokenService.save(Token.builder().username(user.get().getUsername()).accessToken(accessToken).refreshToken(refreshToken).build());

        redisTokenService.save(RedisToken.builder().id(user.get().getUsername()).accessToken(accessToken).refreshToken(refreshToken).build());


        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.get().getId())
                .build();
    }

    public String logout(HttpServletRequest request) {
        // 1. Lấy từ header Authorization (Chuẩn)
        String authorization = request.getHeader(AUTHORIZATION);

        // 2. Kiểm tra có "Bearer " không
        if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
            throw new InvalidDataException("Token invalid");
        }

        // 3. Cắt chuỗi lấy token
        final String token = authorization.substring(7);

        // 4. Lấy username và xóa trong DB
        final String userName = jwtService.extractUserName(token, TokenType.ACCESS_TOKEN);
        System.out.println("userName: " + userName);

//        tokenService.delete(userName);
        redisTokenService.delete(userName);

        return "Deleted!";
    }


    /**
     * Forgot password
     *
     * @param email
     */
    public String forgotPassword(String email) {
        log.info("-----forgetPassword");

        //check email exists or not
        User user = userService.getUserByEmail(email);

        // User is active or inactivated

        if(!user.isEnabled()){
            throw new InvalidDataException("User is not active");
        }
        //generate reset token
        String resetToken = jwtService.generateResetToken(user);

//        tokenService.save(Token.builder()
//                        .username(user.getUsername())
//                        .resetToken(resetToken)
//                .build());

        redisTokenService.save(RedisToken.builder()
                .id(user.getUsername())
                .resetToken(resetToken)
                .build());

        //Send email confirmLink

        String confirmLink = String.format("curl --location 'http://localhost:8080/auth/reset-password' \\\n" +
                "--header 'Accept: */*' \\\n" +
                "--header 'Content-Type: application/json' \\\n" +
                "--header 'data;' \\\n" +
                "--data '%s' ", resetToken);
        System.out.println("confirmLink " + confirmLink);
        log.info("confirmLink={} " , confirmLink);
        return "Sent";
    }

    public String resetPassword(String secretKey) {
        log.info("-----resetPassword");

        final String userName = jwtService.extractUserName(secretKey, TokenType.RESET_TOKEN);
        var user = userService.getByUsername(userName);

        if (!jwtService.isTokenValid(secretKey, user, TokenType.RESET_TOKEN)) {
            throw new InvalidDataException("token is invalid");
        }
        redisTokenService.getById(userName);

        return "Reset";
    }

    public String changePassword(ResetPasswordDTO request) {
        User user = isValidUserByToken(request.getSecretKey());

        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidDataException("Password not match");
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.saveUser(user);
        return "Change";
    }


    public User isValidUserByToken(String secretKey) {
        final String userName = jwtService.extractUserName(secretKey, TokenType.RESET_TOKEN);

        var user = userService.getByUsername(userName);

        if(!user.isEnabled()){
            throw new InvalidDataException("User is not active");
        }

        if(!jwtService.isTokenValid(secretKey, user, TokenType.RESET_TOKEN)){
            throw new InvalidDataException("Not allow access with this token");
        }
        return user;
    }
}
