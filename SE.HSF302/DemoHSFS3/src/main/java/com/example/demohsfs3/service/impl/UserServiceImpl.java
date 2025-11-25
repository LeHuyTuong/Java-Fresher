package com.example.demohsfs3.service.impl;

import com.example.demohsfs3.entity.User;
import com.example.demohsfs3.repository.UserRepository;
import com.example.demohsfs3.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser(User user) {
        return userRepository.save(user) != null; // hoặc != 0 tùy kiểu
    }

    @Override
    public void updateUser(int id,User user) {
        // guardrail: phải có id và tồn tại

        User existUser = userRepository.findById(id).orElse(null);
        if(existUser != null){
            existUser.setUsername(user.getUsername());
            existUser.setPassword(user.getPassword());
            existUser.setRole(user.getRole());
            userRepository.save(existUser);
        }

    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
