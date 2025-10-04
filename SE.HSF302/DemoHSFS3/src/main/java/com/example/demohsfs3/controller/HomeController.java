package com.example.demohsfs3.controller;

import ch.qos.logback.core.model.Model;
import com.example.demohsfs3.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }else if(user.getRole().getRoleName().equals("admin")) {
            return "redirect:/user/list";
        }else{
            return "home";
        }
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";

    }
}
