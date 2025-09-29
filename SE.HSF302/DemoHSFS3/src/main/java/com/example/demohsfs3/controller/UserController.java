package com.example.demohsfs3.controller;

import com.example.demohsfs3.entity.Role;
import com.example.demohsfs3.entity.User;
import com.example.demohsfs3.repository.UserRepository;
import com.example.demohsfs3.service.RoleService;
import com.example.demohsfs3.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/user/list")
    public ModelAndView userList(){
        List<User> users = userService.getAllUsers();
        ModelAndView mav = new ModelAndView(); // set them view frontend
        mav.addObject("users",users);
        mav.setViewName("/user/list");

        return mav;
    }

    @GetMapping("/user/create")
    public ModelAndView userCreate(){
        List<Role> roles = roleService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles",roles);
        mav.addObject("user",new User());
        mav.setViewName("/user/create");
        return mav;
    }

    @PostMapping("/user/create")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/user/list";
    }

    @PatchMapping("/user/update")
    public String updateUser(@RequestParam int id,User user){
        userService.updateUser(id,user);
        return "redirect:/user/list";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("/id") int id){
        userService.deleteUser(id);
        return "redirect:/user/list";
    }

}
