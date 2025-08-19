package com.spring.hello.service;

import com.spring.hello.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    UserEntity createUser(UserEntity user);

    UserEntity findByUserNameAndUserEmail(String userName, String userEmail);

    //get all by limit offset
    Page<UserEntity> findAllUsers(Pageable pageable);

    Page<UserEntity> findByUserName(String username,Pageable pageable);
}
