package com.spring.hello;


import com.spring.hello.entity.feed.FeedEntity;
import com.spring.hello.entity.user.UserEntity;
import com.spring.hello.repository.FeedRepository;
import com.spring.hello.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedRepository feedRepository;

    @Test
    @Transactional
    @Rollback(false)
    void oneToManyTestTwo(){
        //1 new User
        UserEntity user = new UserEntity();
        FeedEntity  feed = new FeedEntity();
        user.setUserName("Tips Go");
        user.setUserEmail("Tipsgo@gmail.com");


        feed.setTitle("feed 02");
        user.setFeedList(List.of(feed));


        feed.setDescription("feed description");
        feed.setUser(user);
        userRepository.save(user);
        //feedRepository.save(feed);
    }
    @Test
    @Transactional
    void selectOneToManyTest(){
        UserEntity user = userRepository.findById(3L).orElseThrow();
        System.out.println(user);
        System.out.println(user.getFeedList());
    }
}
