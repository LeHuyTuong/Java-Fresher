package com.example.demohsfs3.controller;

import com.example.demohsfs3.entity.User;
import com.example.demohsfs3.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(HttpSession session, @RequestParam ("username") String username ,
                               @RequestParam ("password") String password){
            User user = userService.getUser(username,password);
            if(user == null){
                return "redirect:/login";
            }
            session.setAttribute("user",user);
            if(user.getRole().getRoleName().equals("admin")) return "redirect:/user/list";
            else{
                return "redirect:/home";
            }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
