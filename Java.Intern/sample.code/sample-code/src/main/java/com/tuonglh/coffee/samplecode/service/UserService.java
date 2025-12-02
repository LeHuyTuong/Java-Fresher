package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.PageResponse;
import com.tuonglh.coffee.samplecode.dto.response.UserDetailResponse;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserStatus;
import com.tuonglh.coffee.samplecode.model.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService(); // Cung cấp UserDetailsService để cấu hình trong SecurityConfig

    User getUserByEmail(String email);

    User getByUsername(String userName);

    List<String> getAllRolesByUserId(Long userId);

    long saveUser(UserRequestDTO requestDTO) throws MessagingException, UnsupportedEncodingException;

    long saveUser(User user);

    void updateUser(long Userid, UserRequestDTO requestDTO);

    void changeStatus(long Userid, UserStatus status);

    void deleteUser(long Userid);

    UserDetailResponse getUser(long UserId);
    PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy);

    PageResponse<?> getAllUsersWithSortByMultipleColumns(int pageNo, int pageSize, String... sorts); // ~List<String> là 1 biến var (variable agurment), có từ java 11

    PageResponse<?> getAllUsersWithSortByMultipleColumnsAndSearch(int pageNo, int pageSize, String search, String sortsBy);


    void confirmUser(long userId, String secretCode);
}

