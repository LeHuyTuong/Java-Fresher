package com.example.demohsfs3.controller;

import com.example.demohsfs3.entity.Role;
import com.example.demohsfs3.entity.User;
import com.example.demohsfs3.repository.UserRepository;
import com.example.demohsfs3.service.RoleService;
import com.example.demohsfs3.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createUser(@Valid User user,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam("roleId") int roleId){
        if(bindingResult.hasErrors()){
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles",roles);
            return "user/create";
        }
        Role role = roleService.findRoleById(roleId);
        user.setRole(role);
        userService.createUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public ModelAndView updateUserForm(HttpSession session, @PathVariable int id){
        User user = (User)session.getAttribute("user");
        List<Role> roles = roleService.findAll();
        User userToEdit =  userService.getUserById(id);
        ModelAndView mav = new ModelAndView();
        if(user.getRole().getRoleName().equals("admin")){
            mav.addObject("user",userToEdit);
            mav.addObject("roles",roles);
            mav.setViewName("user/update");
        }else{
            return new ModelAndView("redirect:/home");
        }
        return mav;
    }

    @PostMapping("/user/update")
    public String updateUser(HttpSession session, User user, @RequestParam("roleId") int roleId){
        User userSession = (User) session.getAttribute("user");
        if(userSession == null) {
            return "redirect:/login";
        }
        else if(userSession.getRole().getRoleName().equals("admin")){
            Role role = roleService.findRoleById(roleId);
            user.setRole(role);
            userService.updateUser(user.getUserId(),user);
            return "redirect:/user/list";
        }else{
            return "403";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(HttpSession session,@PathVariable int id){
        User userSession  = (User) session.getAttribute("user");
        if(userSession == null) {
            return "redirect:/login";
        }else if(userSession.getRole().getRoleName().equals("admin")){
            userService.deleteUser(id);
            return "redirect:/user/list";
        }
        else{
            return "403";
        }
    }

}
