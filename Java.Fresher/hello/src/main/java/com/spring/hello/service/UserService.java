package com.spring.hello.service;

import com.spring.hello.entity.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity findByUserNameAndUserEmail(String userName, String userEmail);
}
