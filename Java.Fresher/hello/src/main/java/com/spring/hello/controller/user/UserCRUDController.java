package com.spring.hello.controller.user;


import com.spring.hello.entity.user.UserEntity;
import com.spring.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCRUDController {

    @Autowired
    private UserService userService;
    //create User

    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity userEntity){
        return userService.createUser(userEntity);
    }

    @GetMapping("/search")
    public UserEntity searcUser(@RequestParam String name, @RequestParam String email){
        return userService.findByUserNameAndUserEmail(name, email);
    }

}
