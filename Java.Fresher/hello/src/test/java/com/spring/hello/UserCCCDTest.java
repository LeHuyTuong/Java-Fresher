package com.spring.hello;

import com.spring.hello.repository.CCCDRepository;
import com.spring.hello.entity.user.CCCDEntity;
import com.spring.hello.entity.user.UserEntity;
import com.spring.hello.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class UserCCCDTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CCCDRepository cccdRepository;

    @Test
    @Transactional
    @Rollback(false)
    void oneToOneTest(){
        UserEntity user = new UserEntity();
        CCCDEntity cccd = new CCCDEntity();

        user.setUserName("Tuong CCCD");
        user.setUserEmail("tuong@gmail.com");

        cccd.setNumberCCCD("12412412512");
        user.setCccd(cccd);
        userRepository.save(user);
    }
}
