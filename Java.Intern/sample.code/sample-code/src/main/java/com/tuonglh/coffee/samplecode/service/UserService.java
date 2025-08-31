package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.UserDetailResponse;
import com.tuonglh.coffee.samplecode.dto.validation.enums.UserStatus;

import java.util.List;

public interface UserService {

    long saveUser(UserRequestDTO requestDTO);

    void updateUser(long Userid, UserRequestDTO requestDTO);

    void changeStatus(long Userid, UserStatus status);

    void deleteUser(long Userid);

    UserDetailResponse getUser(long UserId);

    List<UserDetailResponse> getAllUsers(int pageNo, int pageSize);
}

