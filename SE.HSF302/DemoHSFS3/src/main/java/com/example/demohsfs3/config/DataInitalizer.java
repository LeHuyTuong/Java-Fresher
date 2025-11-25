package com.example.demohsfs3.config;

import com.example.demohsfs3.entity.Role;
import com.example.demohsfs3.entity.User;
import com.example.demohsfs3.service.RoleService;
import com.example.demohsfs3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component

// chuẩn bị data
public class DataInitalizer implements CommandLineRunner { // muốn chạy thì chạy này trước
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.getUser("admin", "user") != null){
            return ;
        }else {

            Role role1 = new Role();
            role1.setRoleName("admin");
            Role role2 = new Role();
            role2.setRoleName("user");
            roleService.addRole(role1);
            roleService.addRole(role2);

            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword("12345");
            user1.setRole(role1);

            userService.createUser(user1);

            User user2 = new User();

            user2.setUsername("user");
            user2.setPassword("12345");
            user2.setRole(role2);
            userService.createUser(user2);
        }
    }
}
